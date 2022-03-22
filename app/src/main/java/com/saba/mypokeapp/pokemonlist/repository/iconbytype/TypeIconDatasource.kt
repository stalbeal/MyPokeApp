package com.saba.mypokeapp.pokemonlist.repository.iconbytype

import com.saba.mypokeapp.R
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonTypeView
import javax.inject.Inject

class TypeIconDatasource @Inject constructor() {

    fun getIcon(type: String?): PokemonTypeView? {
        return getIconType(type)
    }

    private fun getIconType(type: String?): PokemonTypeView? {
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
            return iconTypes[type]!!
        }

        return null
    }
}
