package com.saba.mypokeapp.pokemonlist.repository

import android.util.Log
import com.saba.mypokeapp.db.AppDatabase
import com.saba.mypokeapp.db.entity.AbilityEntity
import com.saba.mypokeapp.db.entity.StatsEntity
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.model.PokemonItem
import com.saba.mypokeapp.pokemonlist.model.PokemonTypes
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonListService
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonService
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonDetailResponse
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonItemResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonDatasource @Inject constructor(
    private val apiService: ApiPokemonListService,
    private val apiPokemonService: ApiPokemonService,
    private val appDatabase: AppDatabase
) {

    suspend fun getList(limit: Int, offset: Int): List<Pokemon> {

        val pokemonEntities = appDatabase.pokemonDao().getAll()
        Log.i("***Limit", limit.toString())
        Log.i("***dbSize", pokemonEntities.size.toString())
        Log.i("***offset", offset.toString())
        if (pokemonEntities.size == offset) {

            return getListFromService(limit, offset)
        }

       // val stats = appDatabase.pokemonDao().getPokemonStats()
        //val ability = appDatabase.pokemonDao().getPokemonAbilities()

        return pokemonEntities.map { pokemon ->
            Pokemon(
                pokemon.id,
                pokemon.name,
                pokemon.image,
                pokemon.order,
                pokemon.firstType!!,
                pokemon.secondType
            )
        }
    }

    private fun mustCallService(
        pokemonEntityEntities: List<com.saba.mypokeapp.db.entity.PokemonEntity>,
        offset: Int
    ) = pokemonEntityEntities.isEmpty() || pokemonEntityEntities.size < offset

    private suspend fun getListFromService(limit: Int, offset: Int): List<Pokemon> =
        coroutineScope {
            val response = apiService.getPokemonList(limit, offset)

            response.results.map {
                async {
                    getDetail(it)
                }
            }.awaitAll()
        }

    private suspend fun getDetail(itemResult: ApiPokemonItemResponse): Pokemon {
        val detail = apiPokemonService.getPokemonDetail(itemResult.name)
        Log.i("***pokemon", detail.id.toString() + " " + detail.name)
        val id = appDatabase.pokemonDao().insert(
            com.saba.mypokeapp.db.entity.PokemonEntity(
                detail.id,
                detail.name,
                detail.height,
                detail.weight,
                detail.baseExperience,
                detail.isDefault,
                detail.locationAreaEncounters,
                detail.order,
                detail.sprites.other.home.frontDefault,
                detail.sprites.other.officialArtwork.frontDefault,
                if (detail.types.isNotEmpty()) detail.types[0].type.name else null,
                if (detail.types.size > 1) detail.types[1].type.name else null
            )
        )

        detail.abilities.forEach {
            appDatabase.abilitiesDao().insert(
                AbilityEntity(
                    abilityId = it.details.name,
                    pokemonId = id.toInt(),
                    name = it.details.name,
                    isHidden = it.isHidden,
                    slot = it.slot
                )
            )
        }

        detail.stats.forEach {
            appDatabase.statsDao().insert(
                StatsEntity(
                    statId = it.type.name,
                    pokemonId = id.toInt(),
                    name = it.type.name,
                    baseStat = it.baseStat,
                    effort = it.effort,
                    url = it.type.url
                )
            )
        }


        return Pokemon(
            detail.id, detail.name, detail.sprites.other.officialArtwork.frontDefault, detail.order,
            detail.types[0].type.name,
            if (detail.types.size > 1) detail.types[1].type.name else null
        )
    }

    private fun getTypeList(detail: ApiPokemonDetailResponse) =
        detail.types.map {
            PokemonTypes(it.slot, PokemonItem(it.type.name, it.type.url))
        }
}

