package com.saba.mypokeapp.pokemonlist.network

import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonDetailResponse
import retrofit2.http.GET

interface ApiPokemonService {

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        name: String
    ): ApiPokemonDetailResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetailById(
        id: Int
    ): ApiPokemonDetailResponse
}
