package com.saba.mypokeapp.db.dao

import androidx.room.*
import com.saba.mypokeapp.db.entity.Ability

@Dao
interface  AbilityDao {

    @Query("SELECT * FROM ability")
    fun getAll(): List<Ability>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg ability: Ability)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ability: Ability): Long

    @Query("SELECT * FROM ability WHERE ability_id = :id LIMIT 1")
    fun findById(id: Int): Ability

    @Delete
    fun delete(ability: Ability)
}

