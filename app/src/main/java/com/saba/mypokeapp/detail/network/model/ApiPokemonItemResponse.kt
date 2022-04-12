package com.saba.mypokeapp.detail.network.model

import com.google.gson.annotations.SerializedName

data class ApiPokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
