package com.dogbreeds

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("dogs.json") // Adjust the endpoint accordingly
    fun getDogBreeds(): Call<List<DogBreed>>
}
