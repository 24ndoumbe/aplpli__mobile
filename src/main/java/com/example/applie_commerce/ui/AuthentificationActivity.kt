package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applie_commerce.databinding.ActivityAuthentificationBinding
import com.example.applie_commerce.api.RetrofitClient
import com.example.applie_commerce.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthentificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAuthentificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener{
            val login = binding.editTextText2.text.toString()
            val pass = binding.editTextTextPassword2.text.toString()

            if (login.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val requestBody = mapOf(
                "email" to login,
                "motDePasse" to pass
            )

            RetrofitClient.apiService.loginClient(requestBody).enqueue(object :
                Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            println("Réponse de l'API : $apiResponse") // Log la réponse
                            when (apiResponse.status) {
                                "ok" -> {
                                    println("Connexion réussie") // Log de succès
                                    Toast.makeText(
                                        this@AuthentificationActivity,
                                        apiResponse.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(
                                        this@AuthentificationActivity,
                                        RayonActivity::class.java
                                    )
                                    startActivity(intent)
                                    finish()
                                }

                                "unauthorized" -> {
                                    println("Connexion échouée : ${apiResponse.message}") // Log d'échec
                                    Toast.makeText(
                                        this@AuthentificationActivity,
                                        apiResponse.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    println("Statut inconnu : ${apiResponse.status}") // Log de statut inconnu
                                    Toast.makeText(
                                        this@AuthentificationActivity,
                                        "Statut inconnu: ${apiResponse.status}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        } else {
                            println("Réponse de l'API vide ou nulle") // Log si la réponse est nulle
                            Toast.makeText(
                                this@AuthentificationActivity,
                                "Réponse de l'API invalide",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        println("Erreur HTTP : ${response.code()}") // Log d'erreur HTTP
                        Toast.makeText(
                            this@AuthentificationActivity,
                            "Erreur HTTP: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    println("Erreur réseau : ${t.message}") // Log d'erreur réseau
                    Toast.makeText(
                        this@AuthentificationActivity,
                        "Erreur réseau: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

        }
    }
}