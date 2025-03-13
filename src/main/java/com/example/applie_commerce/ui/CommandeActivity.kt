package com.example.applie_commerce.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applie_commerce.R
import com.example.applie_commerce.adapters.CommandeAdapter
import com.example.applie_commerce.api.Commande
import com.example.applie_commerce.api.CommandeResponse
import com.example.applie_commerce.api.RetrofitClient
import com.example.applie_commerce.databinding.ActivityCommandeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommandeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommandeBinding
    private lateinit var adapter: CommandeAdapter
    private val commandes = mutableListOf<Commande>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_commande)
        binding = ActivityCommandeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadCommandesFromApi()

        binding.fabAddCommande.setOnClickListener {
            addFakeCommande()
        }
    }

    private fun setupRecyclerView() {
        adapter = CommandeAdapter(commandes) { commande -> deleteCommande(commande) }
        binding.recyclerViewCommandes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCommandes.adapter = adapter
    }

    private fun loadCommandesFromApi() {
        RetrofitClient.apiService.getCommandes().enqueue(object : Callback<List<Commande>> {
            override fun onResponse(call: Call<List<Commande>>, response: Response<List<Commande>>) {
                if (response.isSuccessful) {
                    commandes.clear()
                    commandes.addAll(response.body() ?: emptyList())
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Commande>>, t: Throwable) {
                Toast.makeText(this@CommandeActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun deleteCommande(commande: Commande) {
        commandes.remove(commande)
        adapter.notifyDataSetChanged()
    }

    private fun addFakeCommande() {
        val newCommande = Commande(
            idUrl = "",
            type = "commandes",
            id = commandes.size + 1,
            libelle = "Nouvelle commande",
            quantite = (1..10).random(),
            produits = emptyList()  // Liste vide par défaut
        )
        commandes.add(newCommande)
        adapter.notifyDataSetChanged()


    }
}