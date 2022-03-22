package com.saba.mypokeapp.pokemonlist.repository.iconbytype


import com.saba.mypokeapp.pokemonlist.ui.model.PokemonTypeView

interface PokemonIconByTypeRepository {
    suspend fun getIcon(type: String?): PokemonTypeView?

}
