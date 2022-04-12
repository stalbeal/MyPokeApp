package com.saba.mypokeapp.detail.usecase

import com.saba.mypokeapp.detail.model.PokemonDetail
import com.saba.mypokeapp.detail.repository.PokemonDetailRepository

class GetPokemonDetailUseCase(private val repository: PokemonDetailRepository) {

    suspend fun execute(id: Int) : PokemonDetail {
        return repository.getPokemonDetail(id)
    }
}
