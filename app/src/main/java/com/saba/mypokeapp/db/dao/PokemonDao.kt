package com.saba.mypokeapp.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.saba.mypokeapp.db.entity.Pokemon
import com.saba.mypokeapp.db.entity.PokemonAbilities
import com.saba.mypokeapp.db.entity.PokemonStats

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getAll(): List<Pokemon>

    @Transaction
    @Query("SELECT * FROM pokemon")
    fun getPokemonAbilities(): List<PokemonAbilities>

    @Transaction
    @Query("SELECT * FROM pokemon")
    fun getPokemonStats(): List<PokemonStats>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg pokemon: Pokemon)

    @Insert(onConflict = REPLACE)
    fun insert(pokemon: Pokemon): Long

    @Query("SELECT * FROM pokemon WHERE id = :id LIMIT 1")
    fun findById(id: Int): Pokemon

    @Delete
    fun delete(pokemon: Pokemon)
}

