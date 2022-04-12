package com.saba.mypokeapp.detail.repository

import com.saba.mypokeapp.db.AppDatabase
import com.saba.mypokeapp.db.entity.AbilityEntity
import com.saba.mypokeapp.db.entity.PokemonEntity
import com.saba.mypokeapp.db.entity.StatsEntity
import com.saba.mypokeapp.detail.model.*
import com.saba.mypokeapp.detail.network.ApiPokemonDetailService
import com.saba.mypokeapp.pokemonlist.model.Ability
import com.saba.mypokeapp.pokemonlist.model.PokemonBasicInfo
import com.saba.mypokeapp.pokemonlist.model.PokemonItem
import com.saba.mypokeapp.pokemonlist.model.PokemonStats
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonService
import com.saba.mypokeapp.pokemonlist.network.model.ApiPokemonItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailDatasource @Inject constructor(
    private val apiPokemonService: ApiPokemonService,
    private val apiPokemonDetailService: ApiPokemonDetailService,
    private val appDatabase: AppDatabase
) {

    suspend fun getDetail(id: Int): PokemonDetail {

        return coroutineScope {
            val detail = apiPokemonService.getPokemonDetailById(id)

            val damageRelations =
                withContext(Dispatchers.Default) {
                    apiPokemonDetailService.getDamageRelationsByType(detail.name)
                }

            val pokemonSpecieDetail =
                withContext(Dispatchers.Default) {
                    apiPokemonDetailService.getPokemonSpecieDetail(detail.name)
                }

            val evolutionChain =
                withContext(Dispatchers.Default) {
                    apiPokemonDetailService.getPokemonEvolutionChain(pokemonSpecieDetail.evolutionChain.url)
                }

            return@coroutineScope PokemonDetail(
                detail.id,
                detail.name,
                detail.sprites.other.home.frontDefault,
                detail.order,
                pokemonSpecieDetail.captureRate,
                pokemonSpecieDetail.baseHappiness,
                Physiology(
                    detail.height,
                    detail.weight,
                    pokemonSpecieDetail.genderRate,
                    pokemonSpecieDetail.formsSwitchable,
                    pokemonSpecieDetail.growthRate.name,
                    pokemonSpecieDetail.hasGenderDifferences,
                    pokemonSpecieDetail.isBaby,
                    pokemonSpecieDetail.shape.name,
                    pokemonSpecieDetail.color.name,
                    pokemonSpecieDetail.varieties.map {
                        PokemonVariety(it.language.name, it.isDefault)
                    }),
                Singularity(
                    pokemonSpecieDetail.isLegendary,
                    pokemonSpecieDetail.isMythical
                ),
                pokemonSpecieDetail.pokedexNumbers.map {
                    PokedexPosition(it.pokedex.name, it.number)
                },
                pokemonSpecieDetail.generation.name,
                pokemonSpecieDetail.habitat.name,
                Descriptions(
                    pokemonSpecieDetail.names.map {
                        PokemonName(it.language.name, it.name)
                    },
                    pokemonSpecieDetail.genera.map {
                        Genus(it.genus, it.language.name)
                    },
                    FlavorTextEntry(
                        pokemonSpecieDetail.flavorTextEntries.text,
                        pokemonSpecieDetail.flavorTextEntries.language.name,
                        pokemonSpecieDetail.flavorTextEntries.version.name
                    ),
                    PokemonDescription(
                        pokemonSpecieDetail.formDescriptions.description,
                        pokemonSpecieDetail.formDescriptions.language.name
                    )
                ),
                EvolutionChain(
                    pokemonSpecieDetail.evolvesFrom?.name,
                    if (evolutionChain.chain.evolvesTo.isNotEmpty()) evolutionChain.chain.evolvesTo[0].species.name else null,
                    pokemonSpecieDetail.evolutionChain.url
                ),
                DamageByType(damageRelations.id,
                    damageRelations.name,
                    damageRelations.doubleDamageFrom.map { it.name },
                    damageRelations.halfDamageFrom.map { it.name },
                    damageRelations.noDamageFrom.map { it.name },
                    damageRelations.doubleDamageTo.map { it.name },
                    damageRelations.halfDamageTo.map { it.name },
                    damageRelations.noDamageTo.map { it.name },
                    damageRelations.moves.map { it.name })
            )
        }
    }

    private suspend fun getDetailFromService(itemResult: ApiPokemonItemResponse): PokemonBasicInfo {
        val detail = apiPokemonService.getPokemonDetail(itemResult.name)
        val id = appDatabase.pokemonDao().insert(
            PokemonEntity(
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

        return PokemonBasicInfo(
            detail.id,
            detail.name,
            listOf(),
            detail.height,
            detail.sprites.other.home.frontDefault,
            detail.abilities.map {
                Ability(
                    PokemonItem(it.details.name, it.details.url),
                    it.isHidden,
                    it.slot
                )
            },
            detail.baseExperience,
            detail.locationAreaEncounters,
            detail.order,
            listOf(),
            detail.stats.map {
                PokemonStats(
                    it.baseStat,
                    it.effort,
                    PokemonItem(it.type.name, it.type.url)
                )
            },
            listOf(),
            if (detail.types.isNotEmpty()) detail.types[0].type.name else null,
            if (detail.types.size > 1) detail.types[1].type.name else null,
            detail.weight
        )
    }
}

