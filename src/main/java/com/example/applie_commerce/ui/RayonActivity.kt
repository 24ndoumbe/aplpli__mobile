package com.example.applie_commerce.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.applie_commerce.api.Rayon
import com.example.applie_commerce.api.RayonResponse
import com.example.applie_commerce.api.RetrofitClient
import com.example.applie_commerce.databinding.ActivityRayonBinding
import com.example.applie_commerce.ui.adapter.RayonAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RayonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRayonBinding
    private lateinit var rayonAdapter: RayonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRayonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration du RecyclerView avec GridLayout
        binding.recyclerViewRayon.layoutManager = GridLayoutManager(this, 2)

        // Appeler l'API pour récupérer les rayons
        fetchRayons()
    }

    private fun fetchRayons() {
        val call = RetrofitClient.apiService.getRayons()
        call.enqueue(object : Callback<RayonResponse> {
            override fun onResponse(call: Call<RayonResponse>, response: Response<RayonResponse>) {
                if (response.isSuccessful) {
                    val rayons = response.body()?.rayons ?: emptyList()
                    println("Nombre de rayons récupérés : ${rayons.size}") // ✅ Affiche le bon nombre d'éléments

                    rayonAdapter = RayonAdapter(rayons)
                    binding.recyclerViewRayon.adapter = rayonAdapter
                } else {
                    println("Erreur API : ${response.code()}")
                    Toast.makeText(this@RayonActivity, "Erreur de récupération des rayons", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RayonResponse>, t: Throwable) {
                println("Échec de la requête : ${t.message}")
                Toast.makeText(this@RayonActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
