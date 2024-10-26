package com.contacts_application

data class Contact (
    val avatarUrl: String,
    val email: String,
    val firstName: String,
    val id: String,
    val isFavourite: Boolean,
    val lastName: String,
    val mobileNumber: String
)