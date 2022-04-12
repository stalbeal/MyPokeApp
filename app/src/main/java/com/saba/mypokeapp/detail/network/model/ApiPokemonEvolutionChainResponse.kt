package com.saba.mypokeapp.detail.network.model

import com.google.gson.annotations.SerializedName
import com.saba.mypokeapp.pokemonlist.network.model.ApiMoveResponse

data class ApiPokemonEvolutionChainResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("chain")
    val chain: ApiEvolutionInfoResponse,

    @SerializedName("chababy_trigger_itemin")
    val babyTriggerItem: ApiEvolutionInfoResponse,

    )

data class ApiPokemonEvolutionRules(

    @SerializedName("gender")
    val gender: Int?,

    @SerializedName("held_item")
    val heldItem: ApiPokemonItemResponse,

    @SerializedName("item")
    val item: ApiPokemonItemResponse?,

    @SerializedName("known_move")
    val knownMove: ApiMoveResponse?,

    @SerializedName("known_move_type")
    val knownMoveType: ApiPokemonItemResponse?,

    @SerializedName("location")
    val location: ApiLocationResponse?,

    @SerializedName("min_affection")
    val minAffection: Int?,

    @SerializedName("min_beauty")
    val minBeauty: Int?,

    @SerializedName("min_happiness")
    val minHappiness: Int?,

    @SerializedName("min_level")
    val minLevel: Int?,

    @SerializedName("needs_overworld_rain")
    val needsOverworldRain: Boolean,

    @SerializedName("party_species")
    val partySpecies: ApiPokemonSpeciesResponse?,

    @SerializedName("party_type")
    val partyType: ApiPokemonTypeInfoResponse?,

    @SerializedName("relative_physical_stats")
    val relativePhysicalStats: Int?,

    @SerializedName("time_of_day")
    val timeOfDay: String?,

    @SerializedName("trade_species")
    val tradeSpecies: ApiPokemonSpeciesResponse?,

    @SerializedName("trigger")
    val trigger: ApiPokemonItemResponse?,

    @SerializedName("turn_upside_down")
    val turnUpsideDown: Boolean
)

data class ApiEvolutionInfoResponse(

    @SerializedName("evolution_details")
    val evolutionDetails: List<ApiPokemonEvolutionRules>,

    @SerializedName("evolves_to")
    val evolvesTo: List<ApiEvolutionInfoResponse>,

    @SerializedName("is_baby")
    val isBaby: Boolean,

    @SerializedName("species")
    val species: ApiPokemonItemResponse

)
