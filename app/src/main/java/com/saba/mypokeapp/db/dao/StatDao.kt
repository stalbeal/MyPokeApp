package com.saba.mypokeapp.db.dao

import androidx.room.*
import com.saba.mypokeapp.db.entity.StatsEntity

@Dao
interface  StatDao {

    @Query("SELECT * FROM stats")
    fun getAll(): List<StatsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg stats: StatsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(statsEntity: StatsEntity): Long

    @Query("SELECT * FROM stats WHERE stat_id = :id LIMIT 1")
    fun findById(id: Int): StatsEntity

    @Delete
    fun delete(statsEntity: StatsEntity)
}

