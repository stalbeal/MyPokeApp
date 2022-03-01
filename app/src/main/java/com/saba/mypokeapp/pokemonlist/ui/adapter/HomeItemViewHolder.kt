package com.saba.mypokeapp.pokemonlist.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.saba.mypokeapp.R
import com.saba.mypokeapp.databinding.ItemPokemonBinding
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.util.extensions.loadImageFromUrl
import java.util.*

class HomeItemViewHolder(private val binding: ItemPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {

        binding.tvNumber.text =
            itemView.context.getString(R.string.pokemon_number, pokemon.order.toString())
        binding.tvImage.loadImageFromUrl(pokemon.image)
        binding.tvName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else
                it.toString()
        }

    }
}
