package com.contacts_application

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("contactbook/v1/getlist")
    fun getContacts(): Call<List<Contact>>

}