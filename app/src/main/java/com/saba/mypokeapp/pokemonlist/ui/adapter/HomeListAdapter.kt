package com.saba.mypokeapp.pokemonlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saba.mypokeapp.databinding.ItemPokemonBinding
import com.saba.mypokeapp.pokemonlist.model.Pokemon

class HomeListAdapter() :
    RecyclerView.Adapter<HomeItemViewHolder>() {

    private val pokemonList: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val binding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int = pokemonList.size

    fun addItems(items: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(items)
    }
}
