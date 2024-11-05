package com.example.contacts_application2
import android.graphics.Bitmap

data class Contact(
    var name: String,
    var phoneNumber: String,
    var image: Bitmap? = null
)