package com.example.simpleapp

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleapp.api.AnimalApi
import com.example.simpleapp.model.Animal
import com.example.simpleapp.model.AnimalResponse
import com.example.simpleapp.model.animalMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnimalViewModel(
    private val client: AnimalApi
) : ViewModel() {

    private val _animalListUI = MutableLiveData<AnimalListUI>()
    val animalListUI: LiveData<AnimalListUI> = _animalListUI

    fun fetchAnimalList() {
        _animalListUI.value = AnimalListUI.Loading(true)

        client.fetchAnimalListFromTMDB("hippo").enqueue(object : Callback<List<AnimalResponse>> {
            override fun onResponse(call: Call<List<AnimalResponse>>, response: Response<List<AnimalResponse>>) {
                if (!response.isSuccessful) {
                    _animalListUI.value = AnimalListUI.Error(R.string.error_general)
                    return
                }

                val animalList = response.body()

                if (animalList != null) {
                    _animalListUI.value = AnimalListUI.Success(animalList.map(animalMapper))
                    _animalListUI.value = AnimalListUI.Loading(false)
                } else {
                    _animalListUI.value = AnimalListUI.Empty
                }
            }

            override fun onFailure(call: Call<List<AnimalResponse>>, t: Throwable) {
                _animalListUI.value = AnimalListUI.Error(R.string.error_general)
            }
        })
    }

    fun searchAnimals(query: String) {
        if (query.isEmpty()) {
            return
        }

        _animalListUI.value = AnimalListUI.Loading(true)

        client.fetchAnimalListFromTMDB(query).enqueue(object : Callback<List<AnimalResponse>> {
            override fun onResponse(call: Call<List<AnimalResponse>>, response: Response<List<AnimalResponse>>) {
                if (!response.isSuccessful) {
                    _animalListUI.value = AnimalListUI.Error(R.string.error_general)
                    return
                }

                val animalList = response.body()

                Log.d("searchAnimals", "Search result: $animalList")

                if (animalList != null) {
                    _animalListUI.value = AnimalListUI.Success(animalList.map(animalMapper))
                } else {
                    _animalListUI.value = AnimalListUI.Empty
                }
                _animalListUI.value = AnimalListUI.Loading(false)
            }

            override fun onFailure(call: Call<List<AnimalResponse>>, t: Throwable) {
                Log.e("searchAnimals", "Request failed: ${t.message}", t)
                _animalListUI.value = AnimalListUI.Error(R.string.error_general)
                _animalListUI.value = AnimalListUI.Loading(false)
            }
        })
    }
}

sealed interface AnimalListUI {
    data class Loading(val isLoading: Boolean) : AnimalListUI
    data class Error(@StringRes val errorMessage: Int) : AnimalListUI
    data class Success(val animalList: List<Animal>) : AnimalListUI
    data object Empty : AnimalListUI
}
