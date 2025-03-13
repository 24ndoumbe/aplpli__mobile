package com.example.applie_commerce.api

import com.google.gson.annotations.SerializedName

class ProduitResponse (
    val id: Int,

    @SerializedName("nom_produit")
    val nom: String,

    @SerializedName("desp_produit")
    val description: String,

    @SerializedName("prix_produit")
    val prix: String,

    @SerializedName("lieu_produit")
    val lieu: String,

    @SerializedName("fk_rayon")
    val fkRayon: String,

    @SerializedName("quantite")
    var quantite: Int = 1
){
    // Fonction pour convertir prix en Double
    fun getPrixAsDouble(): Double {
        return prix.toDoubleOrNull() ?: 0.0
    }
}