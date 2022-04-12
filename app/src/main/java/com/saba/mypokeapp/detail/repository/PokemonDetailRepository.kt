package com.saba.mypokeapp.detail.repository

import com.saba.mypokeapp.detail.model.PokemonDetail

interface PokemonDetailRepository {

    suspend fun getPokemonDetail(id: Int) : PokemonDetail
}

