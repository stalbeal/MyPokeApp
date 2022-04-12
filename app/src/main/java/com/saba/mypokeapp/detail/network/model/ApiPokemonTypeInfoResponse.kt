package com.saba.mypokeapp.detail.network.model

import com.google.gson.annotations.SerializedName

data class ApiPokemonTypeInfoResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<ApiPokemonItemResponse>,

    @SerializedName("half_damage_from")
    val halfDamageFrom: List<ApiPokemonItemResponse>,

    @SerializedName("no_damage_from")
    val noDamageFrom: List<ApiPokemonItemResponse>,

    @SerializedName("double_damage_to")
    val doubleDamageTo: List<ApiPokemonItemResponse>,

    @SerializedName("half_damage_to")
    val halfDamageTo: List<ApiPokemonItemResponse>,

    @SerializedName("no_damage_to")
    val noDamageTo: List<ApiPokemonItemResponse>,

    @SerializedName("moves")
    val moves: List<ApiPokemonItemResponse>
)
