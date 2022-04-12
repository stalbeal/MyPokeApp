package com.saba.mypokeapp.pokemonlist.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saba.mypokeapp.R
import com.saba.mypokeapp.databinding.FragmentHomeBinding
import com.saba.mypokeapp.pokemonlist.ui.adapter.HomeListAdapter
import com.saba.mypokeapp.pokemonlist.ui.adapter.PokemonItemActionListener
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), PokemonItemActionListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(requireActivity()) {
            binding.lavLoadingMore.visibility = if (it.loading) {
                View.VISIBLE
            } else {
                View.GONE
            }
            when {
                it.loading && it.pokemonList.isEmpty() -> showLoading()
                it.pokemonList.isNotEmpty() -> {
                    setUpList(it.pokemonList)
                }
                it.loading -> {
                    isLoading = it.loading
                }
            }
        }
    }

    private fun showLoading() {
        binding.lavLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.lavLoading.visibility = View.GONE
    }

    private fun initList() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = HomeListAdapter(this)
        binding.rvList.setHasFixedSize(false)

        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    if (viewModel.state.value?.loading == false && (visibleItemCount + pastVisibleItems >= totalItemCount)) {
                        binding.lavLoadingMore.visibility = View.VISIBLE
                        viewModel.getList()
                    }
                }
            }
        })
    }

    private fun setUpList(pokemonList: List<PokemonView>) {
        hideLoading()

        (binding.rvList.adapter as HomeListAdapter).submitList(pokemonList)
    }

    override fun onClickListener(pokemonId: Int) {
        findNavController().navigate(R.id.action_homeFragment_to_pokemonDetailFragment)
    }
}

