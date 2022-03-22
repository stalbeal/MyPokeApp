package com.saba.mypokeapp.pokemonlist.repository.list

import com.saba.mypokeapp.pokemonlist.model.Pokemon

interface PokemonListRepository {

    suspend fun getList(limit: Int, offset: Int): List<Pokemon>
}

