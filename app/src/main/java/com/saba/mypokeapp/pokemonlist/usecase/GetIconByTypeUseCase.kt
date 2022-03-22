package com.saba.mypokeapp.pokemonlist.usecase

import com.saba.mypokeapp.pokemonlist.repository.iconbytype.PokemonIconByTypeRepository
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonTypeView
import javax.inject.Inject

class GetIconByTypeUseCase @Inject constructor(private val repository: PokemonIconByTypeRepository) {

    suspend fun execute(type:String?): PokemonTypeView? {
        return repository.getIcon(type)
    }
}
