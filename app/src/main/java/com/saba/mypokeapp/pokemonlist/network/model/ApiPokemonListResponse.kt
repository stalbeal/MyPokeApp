package com.saba.mypokeapp.pokemonlist.network.model

import com.google.gson.annotations.SerializedName

data class ApiPokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val nextCall: String,
    @SerializedName("previous") val previousCall: String?,
    @SerializedName("results") val results: List<ApiPokemonItemResponse>
)


data class ApiPokemonItemResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class ApiPokemonDetailResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("forms") val forms: List<ApiPokemonItemResponse>,
    @SerializedName("height") val height: Int,
    @SerializedName("abilities") val abilities: List<ApiAbilityResponse>,
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String,
    @SerializedName("moves") val moves: List<ApiMoveResponse>,
    @SerializedName("order") val order: Int,
    @SerializedName("species") val species: ApiPokemonItemResponse,
    @SerializedName("stats") val stats: List<ApiPokemonStatResponse>,
    @SerializedName("sprites") val sprites: ApiSpritesResponse,
    @SerializedName("types") val types: List<ApiPokemonTypesResponse>,
    @SerializedName("weight") val weight: Int
)

data class ApiPokemonTypesResponse(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: ApiPokemonItemResponse
)

data class ApiPokemonStatResponse(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val type: ApiPokemonItemResponse
)


data class ApiSpritesResponse(
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("back_female") val backFemale: String,
    @SerializedName("back_shiny") val backShiny: String,
    @SerializedName("back_shiny_female") val backShinyFemale: String,
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_female") val frontFemale: String,
    @SerializedName("front_shiny") val frontShiny: String,
    @SerializedName("front_shiny_female") val frontShinyFemale: String,
    @SerializedName("other") val other: ApiOtherSpritesResponse
)

data class ApiOtherSpritesResponse(
    @SerializedName("dream_world") val dreamWorld: ApiImagesResponse,
    @SerializedName("home") val home: ApiImagesResponse,
    @SerializedName("official-artwork") val officialArtwork: ApiImagesResponse
)


data class ApiImagesResponse(
    @SerializedName("back_default") val backDefault: String = "",
    @SerializedName("back_female") val backFemale: String = "",
    @SerializedName("back_shiny") val backShiny: String = "",
    @SerializedName("back_shiny_female") val backShinyFemale: String = "",
    @SerializedName("front_default") val frontDefault: String = "",
    @SerializedName("front_female") val frontFemale: String = "",
    @SerializedName("front_shiny") val frontShiny: String = "",
    @SerializedName("front_shiny_female") val frontShinyFemale: String = "",
    @SerializedName("front_gray") val front_Gray: String = "",
    @SerializedName("front_transparent") val frontTransparent: String = "",
    @SerializedName("back_gray") val backGray: String = "",
    @SerializedName("back_transparent") val back_Transparent: String = ""
)

data class ApiMoveResponse(@SerializedName("move") val move: ApiPokemonItemResponse)

data class ApiAbilityResponse(
    @SerializedName("ability") val details: ApiPokemonItemResponse,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)
