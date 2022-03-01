package com.saba.mypokeapp.pokemonlist.network

import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiPokemonListService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ApiPokemonListResponse

    @GET
    suspend fun getByUrl(@Url url: String): ApiPokemonListResponse

}
