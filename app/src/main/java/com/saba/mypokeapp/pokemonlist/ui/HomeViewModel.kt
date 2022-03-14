package com.saba.mypokeapp.pokemonlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saba.mypokeapp.model.CoroutineDispatchers
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val coroutineDispatchers: CoroutineDispatchers
) :
    ViewModel() {

    private val _state = MutableLiveData(State())

    val state: LiveData<State> = _state

    private var limit = 21
    private var offset = 0

    init {
        getList()
    }



    fun getList() {
        viewModelScope.launch {
            val cs = _state.value ?: return@launch

            _state.value = _state.value?.copy(loading = true)

            val response = withContext(coroutineDispatchers.io) {
                getPokemonListUseCase.execute(limit, cs.pokemonList.size)
            }

            _state.value = _state.value?.copy(
                loading = false,
                pokemonList = cs.pokemonList + response
            )
        }
    }

    data class State(
        val loading: Boolean = true,
        val pokemonList: List<Pokemon> = mutableListOf()
    )
}
