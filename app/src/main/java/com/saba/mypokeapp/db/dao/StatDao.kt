package com.saba.mypokeapp.db.dao

import androidx.room.*
import com.saba.mypokeapp.db.entity.Stats

@Dao
interface  StatDao {

    @Query("SELECT * FROM stats")
    fun getAll(): List<Stats>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg stats: Stats)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stats: Stats): Long

    @Query("SELECT * FROM stats WHERE stat_id = :id LIMIT 1")
    fun findById(id: Int): Stats

    @Delete
    fun delete(stats: Stats)
}

