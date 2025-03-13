package com.example.applie_commerce.api

import com.google.gson.annotations.SerializedName

class Produit(
    @SerializedName("@id") val idUrl: String,
    @SerializedName("@type") val type: String,
    val id: Int,
    @SerializedName("nomProduit") val nom: String,
    @SerializedName("despProduit") val description: String,
    @SerializedName("prixProduit") val prix: Double,

    @SerializedName("lieuProduit") val lieu: String,
    @SerializedName("fkRayon") val fkRayon: String
)
