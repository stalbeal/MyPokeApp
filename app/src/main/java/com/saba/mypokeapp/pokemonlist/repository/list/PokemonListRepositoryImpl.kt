package com.saba.mypokeapp.pokemonlist.repository.list

import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.repository.PokemonDatasource
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val dataSource: PokemonDatasource
) : PokemonListRepository {

    override suspend fun getList(limit: Int, offset: Int): List<Pokemon> {
        return dataSource.getList(limit, offset)
    }
}

