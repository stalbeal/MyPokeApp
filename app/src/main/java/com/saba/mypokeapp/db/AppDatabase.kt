package com.saba.mypokeapp.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saba.mypokeapp.db.dao.AbilityDao
import com.saba.mypokeapp.db.dao.PokemonDao
import com.saba.mypokeapp.db.dao.StatDao
import com.saba.mypokeapp.db.entity.*

@Database(
    entities = [Pokemon::class, Ability::class, Stats::class],
    version = 1
)
@TypeConverters(
    StringConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun abilitiesDao(): AbilityDao
    abstract fun statsDao(): StatDao
}
