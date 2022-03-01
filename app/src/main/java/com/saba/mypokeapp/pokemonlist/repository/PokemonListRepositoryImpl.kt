package com.saba.mypokeapp.pokemonlist.repository

import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.model.PokemonItem
import com.saba.mypokeapp.pokemonlist.model.PokemonTypes
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonListService
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonService
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonDetailResponse
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonItemResponse
import javax.inject.Inject

class PokemonListRepositoryImpl @Inject constructor(
    private val apiService: ApiPokemonListService,
    private val apiPokemonService: ApiPokemonService
) : PokemonListRepository {

    override suspend fun getList(limit: Int, offset: Int): List<Pokemon> {
        val response = apiService.getPokemonList(limit, offset)

        val mappedResponse = response.results.map {
            getDetail(it, response.nextCall)
        }
        return mappedResponse
    }

    private suspend fun getDetail(itemResult: ApiPokemonItemResponse, nextCall: String): Pokemon {
        val detail = apiPokemonService.getPokemonDetail(itemResult.url)
        return Pokemon(
            detail.id, detail.name, detail.sprites.other.officialArtwork.frontDefault, detail.order,
            getTypeList(detail), nextCall
        )
    }

    private fun getTypeList(detail: ApiPokemonDetailResponse) =
        detail.types.map {
            PokemonTypes(it.slot, PokemonItem(it.type.name, it.type.url))
        }

    override suspend fun getList(url: String): List<Pokemon> {
        val response = apiService.getByUrl(url)

        val mappedResponse = response.results.map {
            getDetail(it, response.nextCall)
        }
        return mappedResponse
    }

}
