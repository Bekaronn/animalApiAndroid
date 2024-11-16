package com.example.simpleapp.api

import com.example.simpleapp.model.AnimalResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimalApi {
    @GET("v1/animals")
    fun fetchAnimalListFromTMDB(@Query("name") query: String): Call<List<AnimalResponse>>
}