package com.saba.mypokeapp.pokemonlist.usecase

import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.repository.PokemonListRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonListRepository) {

    suspend fun execute(limit: Int, offset: Int): List<Pokemon> {

        return repository.getList(limit, offset)
    }

}
