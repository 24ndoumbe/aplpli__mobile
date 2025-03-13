package com.example.applie_commerce.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applie_commerce.R
import com.example.applie_commerce.databinding.ActivityOrderDetailsActBinding

class OrderDetailsActActivity : AppCompatActivity() {private lateinit var binding: ActivityOrderDetailsActBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsActBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Logique pour afficher les détails de la commande
        val orderId = intent.getStringExtra("ORDER_ID") ?: "N/A"
        binding.textOrderId.text = "Order ID: $orderId"
    }
}