package com.example.applie_commerce.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // 🔹 Récupérer la liste des clients
    @GET("clients")
    fun getClients(): Call<List<ClientResponse>>

    // 🔹 Récupérer un client par son ID
    @GET("clients/{id}")
    fun getClientById(@Path("id") clientId: Int): Call<ClientResponse>


    // 🔹 Créer un nouveau client (Inscription)
    @POST("clients")
    fun createClient(@Body client: ClientRequest): Call<ClientResponse>

    @POST("connexion/client")
    fun loginClient(@Body request: Map<String, String>): Call<ApiResponse>

    @GET("produits")  // URL PRODUIT
    fun getProduits(): Call<ProduitResponse>

    @GET("rayons")
    fun getRayons(): Call<RayonResponse>  //pour récupère tous les rayons

    @GET("rayons/{rayonId}")
    fun getRayon(@Path("rayonId") rayonId: Int): Call<Rayon>  // pour récupère un seul rayon par ID

    //  pour récupérer les produits d'un rayon donné
    @GET("rayons/{rayonId}/produits")
    fun getProduitsParRayon(@Path("rayonId") rayonId: Int): Call<List<ProduitResponse>>

    @GET("produits/{produitId}")
    fun getProduitById(@Path("produitId") produitId: Int): Call<ProduitResponse>

    @GET("commandes")
    fun getCommandes(): Call<List<Commande>>  // Récupère toutes les commandes

    @POST("commandes")
    fun createCommande(@Body commande: Commande): Call<Commande>  // Crée une commande


}