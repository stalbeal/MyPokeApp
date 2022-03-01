package com.saba.mypokeapp.pokemonlist.repository

import com.saba.mypokeapp.pokemonlist.model.Pokemon

interface PokemonListRepository {

    suspend fun getList(limit: Int, offset: Int): List<Pokemon>

    suspend fun getList(url: String): List<Pokemon>
}

