package com.saba.mypokeapp.pokemonlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.saba.mypokeapp.databinding.ItemPokemonBinding
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonDiffUtilCallback
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonView

class HomeListAdapter(private val onClickListener: PokemonItemActionListener) :
    ListAdapter<PokemonView, HomeItemViewHolder>(PokemonDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }
}
