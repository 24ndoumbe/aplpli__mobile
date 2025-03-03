package com.example.applie_commerce.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("connexion/clients")
    fun loginClient(@Body request: Map<String, String>): Call<ApiResponse>
}