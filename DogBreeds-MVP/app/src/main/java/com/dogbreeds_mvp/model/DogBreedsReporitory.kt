package com.dogbreeds_mvp.model

import android.content.Context
import com.dogbreeds_mvp.R
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogBreedsRepository {

    private var apiService: ApiInterface? = null

    private fun getApiService(context: Context): ApiInterface {
        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(ApiInterface::class.java)
        }
        return apiService!!
    }

    fun fetchDogBreeds(context: Context, callback: (List<DogBreed>?) -> Unit) {
        val call = getApiService(context).getDogBreeds()
        call.enqueue(object : retrofit2.Callback<List<DogBreed>> {
            override fun onResponse(call: Call<List<DogBreed>>, response: retrofit2.Response<List<DogBreed>>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<List<DogBreed>>, t: Throwable) {
                callback(null)
            }
        })
    }
}
