package com.example.applie_commerce.api

data class ClientRequest(
    val email: String,
    val password: String,
    val prenomClient: String,
    val nomClient: String,
    val adresseClient: String,
    val telephoneClient: String,
    val nbEnfantClient: Int
)
