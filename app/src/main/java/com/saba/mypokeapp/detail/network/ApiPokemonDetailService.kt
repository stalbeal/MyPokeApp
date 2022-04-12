package com.saba.mypokeapp.detail.network

import com.saba.mypokeapp.detail.network.model.ApiEvolutionChainResponse
import com.saba.mypokeapp.detail.network.model.ApiPokemonEvolutionChainResponse
import com.saba.mypokeapp.detail.network.model.ApiPokemonTypeInfoResponse
import com.saba.mypokeapp.detail.network.model.ApiPokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiPokemonDetailService {

    @GET("type/{name}")
    suspend fun getDamageRelationsByType(
        name: String
    ): ApiPokemonTypeInfoResponse

    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecieDetail(
        name: String
    ): ApiPokemonSpeciesResponse

    @GET
    suspend fun getPokemonEvolutionChain(
        @Url url: String
    ): ApiPokemonEvolutionChainResponse
}
