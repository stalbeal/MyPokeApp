package com.saba.mypokeapp.pokemonlist.repository.iconbytype

import com.saba.mypokeapp.pokemonlist.ui.model.PokemonTypeView
import javax.inject.Inject

class PokemonIconByTypeRepositoryImpl @Inject constructor(private  val dataSource: TypeIconDatasource) :
    PokemonIconByTypeRepository {
    override suspend fun getIcon(type: String?): PokemonTypeView? {
        return dataSource.getIcon(type)
    }


}
