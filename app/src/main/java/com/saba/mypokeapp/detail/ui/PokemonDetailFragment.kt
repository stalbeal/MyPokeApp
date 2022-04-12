package com.saba.mypokeapp.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.saba.mypokeapp.databinding.FragmentPokemonDetailBinding
import com.saba.mypokeapp.detail.model.PokemonDetail
import com.saba.mypokeapp.util.ViewModelFactory
import com.saba.mypokeapp.util.extensions.loadImageFromUrl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    @Inject
    lateinit var factory: PokemonDetailViewModel.Factory
    private lateinit var binding: FragmentPokemonDetailBinding

    private val viewModel: PokemonDetailViewModel by viewModels {
        ViewModelFactory.from {
            factory.create(
                requireArguments().getInt("pokemonId")!!
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(requireActivity()) {
            when {
                it.loading -> {}//show loading
                (it.pokemon != null) ->
                    setUpView(it.pokemon)

            }
        }
    }

    private fun setUpView(pokemon: PokemonDetail) {
        binding.ivImage.loadImageFromUrl(pokemon.image)
        binding.tvName.text = pokemon.name
        binding.tvNumber.text = pokemon.id.toString()
        //setUpGender(binding.vGender)
        setUpHeight(pokemon.physiology.height)
        setUpWeight(pokemon.physiology.weight)
    }

    private fun setUpWeight(weight: Int) {
        binding.vHeight.tvValue.text = weight.toString()
    }

    private fun setUpHeight(height: Int) {
        binding.vHeight.tvValue.text = height.toString()
    }

    private fun setUpGender(view: View){

    }
}

