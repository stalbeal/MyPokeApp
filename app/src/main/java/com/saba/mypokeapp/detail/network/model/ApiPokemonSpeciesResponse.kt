package com.saba.mypokeapp.detail.network.model

import com.google.gson.annotations.SerializedName

data class ApiPokemonSpeciesResponse(
    @SerializedName("capture_rate")
    val captureRate: Int,
    @SerializedName("base_happiness")
    val baseHappiness: Int,
    @SerializedName("color")
    val color: ApiPokemonItemResponse,
    @SerializedName("egg_groups")
    val eggGroups: List<ApiPokemonItemResponse>,
    @SerializedName("evolution_chain")
    val evolutionChain: ApiEvolutionChainResponse,
    @SerializedName("evolves_from_species")
    val evolvesFrom: ApiPokemonItemResponse?,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: ApiFlavorTextEntryResponse,
    @SerializedName("form_descriptions")
    val formDescriptions: ApiPokemonDescriptionResponse,
    @SerializedName("forms_switchable")
    val formsSwitchable: Boolean,
    @SerializedName("gender_rate")
    val genderRate: Int,
    @SerializedName("genera")
    val genera: List<ApiGenusResponse>,
    @SerializedName("generation")
    val generation: ApiPokemonItemResponse,
    @SerializedName("growth_rate")
    val growthRate: ApiPokemonItemResponse,
    @SerializedName("habitat")
    val habitat: ApiPokemonItemResponse,
    @SerializedName("has_gender_differences")
    val hasGenderDifferences: Boolean,
    @SerializedName("hatch_counter")
    val hatchCounter: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<ApiPokemonNameResponse>,
    @SerializedName("order")
    val order: Int,
    @SerializedName("pokedex_numbers")
    val pokedexNumbers: List<ApiPokedexPositionResponse>,
    @SerializedName("shape")
    val shape: ApiPokemonItemResponse,
    @SerializedName("varieties")
    val varieties: List<ApiPokemonVarietyResponse>,
)


data class ApiFlavorTextEntryResponse(
    @SerializedName("flavor_text")
    val text: String,
    @SerializedName("language")
    val language: ApiPokemonItemResponse,
    @SerializedName("version")
    val version: ApiPokemonItemResponse
)

data class ApiGenusResponse(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: ApiPokemonItemResponse,
)

data class ApiPokemonNameResponse(
    @SerializedName("language")
    val language: ApiPokemonItemResponse,
    @SerializedName("name")
    val name: String,
)

data class ApiPokemonDescriptionResponse(
    @SerializedName("language")
    val language: ApiPokemonItemResponse,
    @SerializedName("description")
    val description: String,
)

data class ApiPokemonVarietyResponse(
    @SerializedName("pokemon")
    val language: ApiPokemonItemResponse,
    @SerializedName("is_default")
    val isDefault: Boolean,
)

data class ApiPokedexPositionResponse(
    @SerializedName("pokedex")
    val pokedex: ApiPokemonItemResponse,
    @SerializedName("entry_number")
    val number: Int
)

data class ApiEvolutionChainResponse(
    @SerializedName("url")
    val url: String
)




