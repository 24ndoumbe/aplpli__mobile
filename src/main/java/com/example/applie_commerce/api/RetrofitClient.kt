package com.example.applie_commerce.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitClient {

    // URL de base de votre API Symfony
    private const val BASE_URL = "http://10.0.2.2:8000/api/ "

    // Instance de Retrofit
    private val okHttpClient = OkHttpClient.Builder()
        .followRedirects(true) // Suivre les redirections
        .followSslRedirects(true) // Suivre les redirections HTTPS
        .connectTimeout(30, TimeUnit.SECONDS) // Temps d'attente pour la connexion
        .readTimeout(30, TimeUnit.SECONDS) // Temps d'attente pour la lecture
        .writeTimeout(30, TimeUnit.SECONDS) // Temps d'attente pour l'écriture
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}