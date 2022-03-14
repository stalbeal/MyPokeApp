package com.saba.mypokeapp.db.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    //@ColumnInfo(name = "abilities") val abilities: List<Ability>,
    @ColumnInfo(name = "base_experience") val baseExperience: Int,
    @ColumnInfo(name = "is_default") val isDefault: Boolean,
    @ColumnInfo(name = "location_area_encounters") val locationAreaEncounters: String,
//    @ColumnInfo(name = "moves") val moves: List<String>,
    @ColumnInfo(name = "order") val order: Int,
    //@ColumnInfo(name = "stats") val stats: List<PokemonStats>,
    @ColumnInfo(name = "home_image") val homeImage: String,
    @ColumnInfo(name = "art_image") val image: String,
    @ColumnInfo(name = "first_type") val firstType: String?,
    @ColumnInfo(name = "second_type") val secondType: String?
)

@Entity(tableName = "ability", primaryKeys = ["ability_id", "pokemon_id"])
data class Ability(
    @ColumnInfo(name = "ability_id") val abilityId: String,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_hidden") val isHidden: Boolean,
    @ColumnInfo(name = "slot") val slot: Int
)

data class PokemonAbilities(

    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id"
    ) val abilities: List<Ability>
)

data class PokemonStats(

    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemon_id"
    ) val stats: List<Stats>
)

/*@Entity(tableName = "type")
data class Type(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type_id") val typeId: Int = 0,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "slot") val slot: Int,
    @ColumnInfo(name = "name") val type: String
)*/

@Entity(tableName = "stats", primaryKeys = ["stat_id", "pokemon_id"])
data class Stats(
    @ColumnInfo(name = "stat_id") val statId: String,
    @ColumnInfo(name = "pokemon_id") val pokemonId: Int,
    @ColumnInfo(name = "base_stat") val baseStat: Int,
    @ColumnInfo(name = "effort") val effort: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
)


class StringConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromGroupTaskMemberList(value: List<String>): String {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            return gson.toJson(value, type)
        }

        @TypeConverter
        @JvmStatic
        fun toGroupTaskMemberList(value: String): List<String> {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(value, type)
        }
    }
}
