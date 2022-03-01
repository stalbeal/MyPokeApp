package com.saba.mypokeapp.pokemonlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saba.mypokeapp.pokemonlist.model.Pokemon
import com.saba.mypokeapp.pokemonlist.usecase.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getPokemonListUseCase: GetPokemonListUseCase) :
    ViewModel() {

    private val _state = MutableLiveData(State())

    val state: LiveData<State> = _state

    private val limit = 21
    private var offset = 0

    init {
        getList()
    }

    private fun getList() {

        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getPokemonListUseCase.execute(limit, offset)

            }
            _state.value = _state.value?.copy(loading = false, pokemonList = response)

        }
    }

    data class State(
        val loading: Boolean = true,
        val pokemonList: List<Pokemon>? = null
    )
}
