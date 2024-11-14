package com.dogbreeds_mvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogBreed(
    val id: Int,
    val name: String?,

    @SerializedName("bred_for")
    val bredFor: String?,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("life_span")
    val lifeSpan: String?,

    val origin: String?,
    val temperament: String?,
    val height: Height,
    val weight: Weight,
    val url: String
) : Parcelable

@Parcelize
data class Height(
    val imperial: String,
    val metric: String
) : Parcelable

@Parcelize
data class Weight(
    val imperial: String,
    val metric: String
) : Parcelable
