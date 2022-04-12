package com.saba.mypokeapp.detail.di

import com.saba.mypokeapp.detail.network.ApiPokemonDetailService
import com.saba.mypokeapp.detail.repository.PokemonDetailRepository
import com.saba.mypokeapp.detail.repository.PokemonDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object PokemonDetailModule {

    @Reusable
    @Provides
    fun providePokemonDetailService(retrofit: Retrofit): ApiPokemonDetailService =
        retrofit.create(ApiPokemonDetailService::class.java)

    @Reusable
    @Provides
    fun providePokemonDetailRepository(repositoryImpl: PokemonDetailRepositoryImpl): PokemonDetailRepository =
        repositoryImpl

}
