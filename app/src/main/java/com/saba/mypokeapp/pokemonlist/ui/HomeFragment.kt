package com.saba.mypokeapp.pokemonlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saba.mypokeapp.databinding.FragmentHomeBinding
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.ui.adapter.HomeListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        binding.lavLoadingMore.visibility = View.GONE
    }

    private fun initList() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = HomeListAdapter()

        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        /*binding.nsContainer.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.
                    // on below line we are making our progress bar visible.
                    if (this.isLoading) {
                        viewModel.getMoreItems()
                        binding.lavLoadingMore.visibility = View.VISIBLE;
                    }

                }
            })*/

        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
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

    private fun setUpList(pokemonList: List<Pokemon>) {
        hideLoading()

        (binding.rvList.adapter as HomeListAdapter).addItems(pokemonList)
        binding.rvList.post {
            binding.rvList.adapter?.notifyDataSetChanged()
        }
    }
}

