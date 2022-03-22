package com.saba.mypokeapp.pokemonlist.usecase

import com.saba.mypokeapp.pokemonlist.repository.list.PokemonListRepository
import com.saba.mypokeapp.pokemonlist.ui.model.PokemonView
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonListRepository,
    private val getIconByTypeUseCase: GetIconByTypeUseCase
) {

    suspend fun execute(limit: Int, offset: Int): List<PokemonView> {

        return repository.getList(limit, offset).map {
            PokemonView(
                it.id, it.name, it.image, it.order,
                firstType = getIconByTypeUseCase.execute(it.firstType),
                secondType = getIconByTypeUseCase.execute(it.secondType)
            )
        }
    }

}
