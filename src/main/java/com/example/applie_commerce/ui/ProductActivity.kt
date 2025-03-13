package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.applie_commerce.api.Produit
import com.example.applie_commerce.api.ProduitResponse
import com.example.applie_commerce.api.Rayon
import com.example.applie_commerce.api.RetrofitClient
import com.example.applie_commerce.databinding.ActivityProductBinding
import com.example.applie_commerce.ui.adapter.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private lateinit var productAdapter: ProductAdapter
    private var rayonId: Int = -1 // ✅ Déclare `rayonId` ici pour qu'il soit accessible partout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Récupération de l'ID du rayon à partir de l'intent
        rayonId = intent.getIntExtra("rayonId", -1)

        // Vérification de l'ID du rayon
        if (rayonId == -1) {
            Toast.makeText(this, "Erreur: ID du rayon non trouvé.", Toast.LENGTH_SHORT).show()
            finish() // Fermer l'activité si l'ID est invalide
            return
        }

        // Initialisation de l'adapter et du RecyclerView
        productAdapter = ProductAdapter(emptyList())
        binding.recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerViewProducts.adapter = productAdapter

        // Chargement des produits en fonction du rayon
        fetchProducts(rayonId)

        binding.buttonVoirPanier.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchProducts(rayonId: Int) {
        RetrofitClient.apiService.getRayon(rayonId).enqueue(object : Callback<Rayon> {
            override fun onResponse(call: Call<Rayon>, response: Response<Rayon>) {
                if (response.isSuccessful) {
                    val rayon = response.body()
                    if (rayon != null) {
                        // Extraire les IDs des produits à partir des URLs
                        val productIds = rayon.produits.map { url ->
                            url.substringAfterLast("/").toInt() // Extrait l'ID à partir de l'URL "/api/produits/5"
                        }

                        // Récupérer chaque produit en faisant plusieurs appels API
                        fetchProductDetails(productIds)
                    }
                } else {
                    Toast.makeText(
                        this@ProductActivity,
                        "Erreur API: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Rayon>, t: Throwable) {
                Toast.makeText(this@ProductActivity, "Erreur: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    // Fonction pour récupérer chaque produit individuellement
    private fun fetchProductDetails(productIds: List<Int>) {
        val productsList = mutableListOf<ProduitResponse>()

        for (productId in productIds) {
            RetrofitClient.apiService.getProduitById(productId).enqueue(object : Callback<ProduitResponse> {
                override fun onResponse(call: Call<ProduitResponse>, response: Response<ProduitResponse>) {
                    if (response.isSuccessful) {
                        val produit = response.body()
                        if (produit != null) {
                            productsList.add(produit)

                            // Si tous les produits sont récupérés, mettez à jour l'adapter
                            if (productsList.size == productIds.size) {
                                productAdapter.updateData(productsList)
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@ProductActivity,
                            "Erreur API: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ProduitResponse>, t: Throwable) {
                    Toast.makeText(
                        this@ProductActivity,
                        "Erreur réseau: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    }
}