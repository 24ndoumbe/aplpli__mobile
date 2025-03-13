package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applie_commerce.R
import com.example.applie_commerce.databinding.ActivityCartBinding
import com.example.applie_commerce.ui.adapter.CartAdapter
import com.example.applie_commerce.ui.adapter.ProductAdapter

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // setContentView(R.layout.activity_cart)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Votre panier est vide", Toast.LENGTH_SHORT).show()
        }

        // Configuration du RecyclerView
        cartAdapter = CartAdapter(CartManager.getCartItems()) { produit, action ->
            when (action) {
                "increase" -> CartManager.increaseQuantity(produit)
                "decrease" -> CartManager.decreaseQuantity(produit)
                "delete" -> CartManager.removeFromCart(produit)
            }
            cartAdapter.updateData(CartManager.getCartItems()) // Met à jour l'affichage
        }

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCart.adapter = cartAdapter

        binding.valider.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                // Naviguer vers l'écran de commande
                val intent = Intent(this, CommandeActivity::class.java)
                startActivity(intent)
            } else {
                // Afficher un message si le panier est vide
                Toast.makeText(this, "Votre panier est vide. Ajoutez des produits au panier.", Toast.LENGTH_SHORT).show()
            }
        }
    }


}