package com.saba.mypokeapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<T : ViewModel> @Inject constructor(
    private val provider: Provider<T>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }

    companion object {
        @JvmStatic
        inline fun <T : ViewModel> from(crossinline provider: () -> T): ViewModelFactory<T> {
            return ViewModelFactory { provider.invoke() }
        }
    }
}
