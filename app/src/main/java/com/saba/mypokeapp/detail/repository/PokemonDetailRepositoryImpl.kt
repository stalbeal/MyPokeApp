package com.saba.mypokeapp.detail.repository

import com.saba.mypokeapp.detail.model.PokemonDetail

class PokemonDetailRepositoryImpl(val datasource: PokemonDetailDatasource) : PokemonDetailRepository {

    override suspend fun getPokemonDetail(id: Int) : PokemonDetail{

        return datasource.getDetail(id)
    }

}
