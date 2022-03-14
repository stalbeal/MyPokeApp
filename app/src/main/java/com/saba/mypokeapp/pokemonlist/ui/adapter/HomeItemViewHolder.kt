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

    private fun getIconType(type: String): PokemonTypeView? {
        val iconTypes = HashMap<String, PokemonTypeView>()
        iconTypes["normal"] = PokemonTypeView("normal", R.drawable.ic_normal, R.color.normal)

        iconTypes["fighting"] = PokemonTypeView("fighting", R.drawable.ic_fight, R.color.fight)

        iconTypes["flying"] = PokemonTypeView("flying", R.drawable.ic_flying, R.color.flying)

        iconTypes["poison"] = PokemonTypeView("poison", R.drawable.ic_poison, R.color.poison)

        iconTypes["ground"] = PokemonTypeView("ground", R.drawable.ic_ground, R.color.ground)

        iconTypes["rock"] = PokemonTypeView("rock", R.drawable.ic_rock, R.color.rock)

        iconTypes["bug"] = PokemonTypeView("bug", R.drawable.ic_bug, R.color.bug)

        iconTypes["ghost"] = PokemonTypeView("ghost", R.drawable.ic_ghost, R.color.ghost)

        iconTypes["steel"] = PokemonTypeView("steel", R.drawable.ic_iron, R.color.iron)

        iconTypes["fire"] = PokemonTypeView("fire", R.drawable.ic_fire, R.color.fire)

        iconTypes["water"] = PokemonTypeView("water", R.drawable.ic_water, R.color.water)

        iconTypes["grass"] = PokemonTypeView("grass", R.drawable.ic_grass, R.color.grass)

        iconTypes["electric"] =
            PokemonTypeView("electric", R.drawable.ic_electric, R.color.electric)

        iconTypes["psychic"] = PokemonTypeView("psychic", R.drawable.ic_psychic, R.color.psychic)

        iconTypes["ice"] = PokemonTypeView("ice", R.drawable.ic_ice, R.color.ice)

        iconTypes["dragon"] = PokemonTypeView("dragon", R.drawable.ic_dragon, R.color.dragon)

        iconTypes["dark"] = PokemonTypeView("dark", R.drawable.ic_dark, R.color.dark)

        iconTypes["fairy"] = PokemonTypeView("fairy", R.drawable.ic_fairy, R.color.fairy)


        if (iconTypes.containsKey(type)) {
            return iconTypes[type]
        }

        return PokemonTypeView("", R.drawable.ic_rock, R.color.white)
    }


}

class PokemonTypeView(val name: String, val icon: Int, val color: Int)
