package com.saba.mypokeapp.pokemonlist.repository

import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.model.PokemonItem
import com.saba.mypokeapp.pokemonlist.model.PokemonTypes
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonDetailResponse
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonItemResponse
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val dataSource: PokemonDatasource
) : PokemonListRepository {

    override suspend fun getList(limit: Int, offset: Int): List<Pokemon> {
        return dataSource.getList(limit, offset)
    }
}
