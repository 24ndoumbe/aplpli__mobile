package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applie_commerce.databinding.ActivityMainBinding
import com.example.applie_commerce.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
       val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration du ViewPager avec TabLayout
        val adapter = TabAdapter(this)
        binding.viewPager.adapter = adapter

        // Associer les onglets avec le ViewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Rayons"
                1 -> tab.text = "Produits"
                2 -> tab.text = "Panier"
                3 -> tab.text = "Commandes"
            }
        }.attach()

        // Redirection vers la page de connexion
        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, AuthentificationActivity::class.java)
            startActivity(intent)
        }

        // Redirection vers la page d'inscription
        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, InscriptionActivity::class.java)
            startActivity(intent)
        }

    }
}