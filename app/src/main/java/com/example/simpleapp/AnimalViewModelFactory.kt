package com.example.simpleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleapp.api.ApiSource

class AnimalViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalViewModel(
            client = ApiSource.client
        ) as T
    }
}