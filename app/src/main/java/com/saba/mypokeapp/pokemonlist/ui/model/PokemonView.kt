package com.saba.mypokeapp.pokemonlist.ui.model

import androidx.recyclerview.widget.DiffUtil

data class PokemonView(
    val id: Int,
    val name: String,
    val image: String,
    val order: Int,
    val firstType: PokemonTypeView?,
    val secondType: PokemonTypeView? = null
)

data class PokemonTypeView(val name: String, val icon: Int, val color: Int)

class PokemonDiffUtilCallback : DiffUtil.ItemCallback<PokemonView>() {

    override fun areContentsTheSame(oldItem: PokemonView, newItem: PokemonView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: PokemonView, newItem: PokemonView): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun getChangePayload(oldItem: PokemonView, newItem: PokemonView): Any? {
        return newItem
    }

}

