package com.saba.mypokeapp.db.dao

import androidx.room.*
import com.saba.mypokeapp.db.entity.AbilityEntity

@Dao
interface  AbilityDao {

    @Query("SELECT * FROM ability")
    fun getAll(): List<AbilityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg abilityEntity: AbilityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(abilityEntity: AbilityEntity): Long

    @Query("SELECT * FROM ability WHERE ability_id = :id LIMIT 1")
    fun findById(id: Int): AbilityEntity

    @Delete
    fun delete(abilityEntity: AbilityEntity)
}

