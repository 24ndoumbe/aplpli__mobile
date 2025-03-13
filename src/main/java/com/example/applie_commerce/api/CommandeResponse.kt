package com.example.applie_commerce.api

import com.google.gson.annotations.SerializedName

class CommandeResponse (
    val id: Int,

    @SerializedName("libelle_commande")
    val libelle: String,

    @SerializedName("quantite_commande")
    val quantite: Int
)