package com.example.applie_commerce.api

import com.google.gson.annotations.SerializedName

class Rayon(
    @SerializedName("@id") val idUrl: String,
    @SerializedName("@type") val type: String,
    val id: Int,
    val libelle: String,
    val produits: List<String>
)
