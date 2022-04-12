package com.saba.mypokeapp.detail.model

import com.saba.mypokeapp.pokemonlist.model.PokemonItem

data class PokemonDetail(
    val id: Int,

    val name: String,

    val image: String,

    val order: Int,

    val captureRate: Int,

    val baseHappiness: Int,

    val physiology: Physiology,

    val singularity: Singularity,

    val pokedexNumbers: List<PokedexPosition>,

    val generation: String,

    val habitat: String,

    val descriptions: Descriptions,

    val evolutionChain: EvolutionChain,

    val damageByType: DamageByType
)

data class Physiology(
    val height: Int,

    val weight: Int,

    val genderRate: Int,

    val formsSwitchable: Boolean,

    val growthRate: String,

    val hasGenderDifferences: Boolean,

    val isBaby: Boolean,

    val shape: String,

    val color: String,

    val varieties: List<PokemonVariety>

)

data class Singularity(
    val isLegendary: Boolean,

    val isMythical: Boolean
)

data class Descriptions(
    val names: List<PokemonName>,
    val genera: List<Genus>,
    val flavorTextEntries: FlavorTextEntry,
    val formDescriptions: PokemonDescription,
)

data class EvolutionChain(
    val evolvesFrom: String?,
    val evolvesTo: String?,
    val evolutionChainUrl: String

)

data class EggDetails(

    val eggGroups: List<PokemonItem>,
    val hatchCounter: Int

)


data class FlavorTextEntry(

    val text: String,

    val language: String,

    val version: String
)

data class Genus(

    val genus: String,

    val language: String
)

data class PokemonName(

    val language: String,

    val name: String
)

data class PokemonDescription(

    val language: String,

    val description: String
)

data class PokemonVariety(

    val language: String,

    val isDefault: Boolean
)

data class PokedexPosition(

    val pokedex: String,

    val number: Int
)

data class DamageByType(
    val id: Int,

    val name: String,

    val doubleDamageFrom: List<String>,

    val halfDamageFrom: List<String>,

    val noDamageFrom: List<String>,

    val doubleDamageTo: List<String>,

    val halfDamageTo: List<String>,

    val noDamageTo: List<String>,

    val moves: List<String>
)
