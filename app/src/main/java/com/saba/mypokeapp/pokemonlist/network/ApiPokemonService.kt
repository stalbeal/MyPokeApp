package com.saba.mypokeapp.pokemonlist.network

import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiPokemonService {

    @GET
    suspend fun getPokemonDetail(
        @Url url : String
    ): ApiPokemonDetailResponse
}
