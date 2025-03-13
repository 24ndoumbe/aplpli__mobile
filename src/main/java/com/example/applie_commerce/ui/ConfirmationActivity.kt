package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applie_commerce.R
import com.example.applie_commerce.api.Produit
import androidx.recyclerview.widget.RecyclerView


import com.example.applie_commerce.databinding.ActivityConfirmationBinding
import com.example.applie_commerce.ui.adapter.OrderDetailsAdapter

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_confirmation)

        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Simuler des données de commande
        val orderId = intent.getStringExtra("ORDER_ID") ?: "N/A"
        val totalAmount = intent.getDoubleExtra("TOTAL_AMOUNT", 0.0)
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD") ?: "Carte bancaire"
        val deliveryAddress = intent.getStringExtra("DELIVERY_ADDRESS") ?: "Non précisé"

        // Simuler une liste de produits commandés
        val products = intent.getSerializableExtra("PRODUCTS_LIST") as? ArrayList<Produit> ?: arrayListOf()

        // Afficher les détails de la commande
        binding.orderNumber.text = "Numéro de commande : $orderId"
        binding.totalAmount.text = "Montant total : $totalAmount €"
        binding.paymentMethod.text = "Mode de paiement : $paymentMethod"
        binding.deliveryAddress.text = "Adresse de livraison : $deliveryAddress"

        // Afficher la liste des produits commandés dans un RecyclerView
        val adapter = OrderDetailsAdapter(products)
        binding.recyclerViewOrderDetails.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewOrderDetails.adapter = adapter

        // Action sur le bouton "Retour à l'accueil"
        binding.buttonReturnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Action sur le bouton "Voir ma commande"
        binding.buttonViewOrder.setOnClickListener {
            val intent = Intent(this, OrderDetailsActActivity::class.java)
            intent.putExtra("ORDER_ID", orderId)
            startActivity(intent)
        }

    }
}