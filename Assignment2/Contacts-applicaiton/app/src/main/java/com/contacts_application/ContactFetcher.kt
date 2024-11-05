package com.contacts_application

import android.content.Context
import android.util.Log
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ContactFetcher {

    fun fetchContacts(context: Context, callback: (ArrayList<Contact>?) -> Unit) {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getContacts()

        retrofitData.enqueue(object : Callback<List<Contact>?> {
            override fun onResponse(call: Call<List<Contact>?>, response: Response<List<Contact>?>) {
                val responseBody = response.body()
                if (responseBody != null) {
                    // Convert List<Contact> to ArrayList<Contact>
                    callback(ArrayList(responseBody))
                } else {
                    callback(null)
                    Log.d("ContactFetcher", "Response body is empty.")
                }
            }

            override fun onFailure(call: Call<List<Contact>?>, t: Throwable) {
                Log.d("ContactFetcher", "onFailure: ${t.message}")
                callback(null)
            }
        })
    }
}
