package com.saba.mypokeapp.pokemonlist.ui.adapter

import android.graphics.drawable.DrawableContainer.DrawableContainerState
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
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
            itemView.context.getString(R.string.pokemon_number, pokemon.id.toString())
        binding.tvImage.loadImageFromUrl(pokemon.image)
        binding.tvName.text = pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else
                it.toString()
        }


        binding.ivFirstType.setImageDrawable(
            AppCompatResources.getDrawable(
                itemView.context,
                getIconType(pokemon.firstType)!!.icon
            )
        )

        val dra: StateListDrawable = binding.vFirstType.background.mutate() as StateListDrawable

        val dcs = dra.constantState as DrawableContainerState
        val drawableItems = dcs.children
        val gradientDrawableChecked = drawableItems[0] as GradientDrawable // item 1
        gradientDrawableChecked.setStroke(
            2,
            itemView.context.resources.getColor(getIconType(pokemon.firstType)!!.color)
        )

        binding.vSecondType.visibility = View.GONE

        pokemon.secondType?.let {
            binding.vSecondType.visibility = View.VISIBLE

            binding.ivSecondType.setImageDrawable(
                AppCompatResources.getDrawable(
                    itemView.context,
                    getIconType(it)!!.icon
                )
            )

            val dra2: StateListDrawable =
                binding.vSecondType.background.mutate() as StateListDrawable

            val dcs2 = dra2.constantState as DrawableContainerState
            val drawableItems2 = dcs2.children
            val gradientDrawableChecked2 = drawableItems2[0] as GradientDrawable // item 1
            gradientDrawableChecked2.setStroke(
                2,
                itemView.context.resources.getColor(getIconType(it)!!.color)
            )
        }
    }

}

class PokemonTypeView(val name: String, val icon: Int, val color: Int)
