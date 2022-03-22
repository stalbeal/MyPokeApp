package com.saba.mypokeapp.di

import android.content.Context
import androidx.room.Room
import com.saba.mypokeapp.db.AppDatabase
import com.saba.mypokeapp.db.dao.PokemonDao
import com.saba.mypokeapp.model.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Main, Dispatchers.IO)

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "pokemons_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
        return appDatabase.pokemonDao()
    }
}
