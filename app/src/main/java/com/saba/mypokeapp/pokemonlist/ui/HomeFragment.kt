package com.saba.mypokeapp.pokemonlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.saba.mypokeapp.databinding.FragmentHomeBinding
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.ui.adapter.HomeListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

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

        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(requireActivity()) {
            when {
                it.loading -> {} //do something
                it.pokemonList != null -> {
                    setUpList(it.pokemonList)
                }
            }
        }
    }

    private fun setUpList(pokemonList: List<Pokemon>) {
        binding.rvList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvList.adapter = HomeListAdapter(pokemonList)
    }
}

