package com.saba.mypokeapp.pokemonlist.di

import com.saba.mypokeapp.pokemonlist.network.ApiPokemonListService
import com.saba.mypokeapp.pokemonlist.network.ApiPokemonService
import com.saba.mypokeapp.pokemonlist.repository.PokemonListRepository
import com.saba.mypokeapp.pokemonlist.repository.PokemonListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PokemonListModule {

    @Reusable
    @Provides
    fun providePokemonListService(retrofit: Retrofit): ApiPokemonListService =
        retrofit.create(ApiPokemonListService::class.java)

    @Reusable
    @Provides
    fun providePokemonService(retrofit: Retrofit): ApiPokemonService =
        retrofit.create(ApiPokemonService::class.java)

    @Reusable
    @Provides
    fun providePokemonListRepository(repositoryImpl: PokemonListRepositoryImpl): PokemonListRepository =
        repositoryImpl
}
