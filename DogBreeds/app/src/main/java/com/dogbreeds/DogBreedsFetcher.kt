package com.dogbreeds

import android.content.Context
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

object DogBreedsFetcher {

    private var cachedDogBreeds: ArrayList<DogBreed>? = null

    fun fetchDogBreeds(context: Context, callback: (ArrayList<DogBreed>?) -> Unit) {

        if (cachedDogBreeds != null) {
            return callback(cachedDogBreeds)
        }

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getDogBreeds()

        retrofitData.enqueue(object : Callback<List<DogBreed>?> {
            override fun onResponse(call: Call<List<DogBreed>?>, response: Response<List<DogBreed>?>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    cachedDogBreeds = ArrayList(responseBody)
                    callback(ArrayList(responseBody))
                } else {
                    callback(ArrayList())
                }
            }

            override fun onFailure(call: Call<List<DogBreed>?>, t: Throwable) {
                callback(null)
            }
        })
    }
}
