package com.saba.mypokeapp.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saba.mypokeapp.detail.model.PokemonDetail
import com.saba.mypokeapp.detail.usecase.GetPokemonDetailUseCase
import com.saba.mypokeapp.model.CoroutineDispatchers
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailViewModel @AssistedInject constructor(
    @Assisted private val pokemonId: Int,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _state = MutableLiveData(State())

    val state: LiveData<State> = _state

    init {
        getDetail(pokemonId)
    }

    private fun getDetail(pokemonId: Int) {
        viewModelScope.launch {
            val detail = withContext(coroutineDispatchers.io) {
                getPokemonDetailUseCase.execute(pokemonId)
            }
            _state.value = _state.value?.copy(
                loading = false,
                pokemon = detail
            )
        }
    }

    data class State(
        val loading: Boolean = true,
        val pokemon: PokemonDetail? = null
    )

    @AssistedFactory
    interface Factory {
        fun create(pokemonId: Int): PokemonDetailViewModel
    }
}

