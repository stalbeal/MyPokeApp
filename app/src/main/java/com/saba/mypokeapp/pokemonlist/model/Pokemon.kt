package com.saba.mypokeapp.pokemonlist.model

data class Pokemon(
    val id: Int,
    val name: String,
    val image: String,
    val order: Int,
    val firstType: String,
    val secondType: String? = null
)

data class PokemonBasicInfo(
    val id: Int,
    val name: String,
    val forms: List<PokemonItem>,
    val height: Int,
    val image: String,
    val abilities: List<Ability>,
    val baseExperience: Int,
    val locationAreaEncounters: String,
    //val moves: List<Move>,
    val order: Int,
    val species: List<PokemonItem>,
    val stats: List<PokemonStats>,
    val sprites: List<Sprites>,
    val firstType: String?,
    val secondType: String? = null,
    val weight: Int
)

data class PokemonTypes(
    val slot: Int,
    val type: PokemonItem
)

data class PokemonStats(
    val baseStat: Int,
    val effort: Int,
    val type: PokemonItem
)

data class Sprites(
    val backDefault: String,
    val backFemale: String,
    val backShiny: String,
    val backShinyFemale: String,
    val frontDefault: String,
    val frontFemale: String,
    val frontShiny: String,
    val frontShinyFemale: String,
    val other: OtherSprites
)

data class OtherSprites(
    val dreamWorld: Images,
    val home: Images,
    val officialArtwork: Images
)


data class Images(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?,
    val front_Gray: String,
    val frontTransparent: String,
    val backGray: String,
    val back_Transparent: String
)

data class Move(
    val move: PokemonItem
)

data class Ability(
    val details: PokemonItem,
    val isHidden: Boolean,
    val slot: Int
)
