package com.example.applie_commerce.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applie_commerce.R
import com.example.applie_commerce.api.ProduitResponse
import com.example.applie_commerce.databinding.ActivityValidateCartBinding
import com.example.applie_commerce.ui.adapter.CartAdapter

class ValidateCartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidateCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidateCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisation du RecyclerView et de l'adaptateur
        cartAdapter = CartAdapter(CartManager.getCartItems(), ::onCartAction) // onAction est passé ici
        binding.recyclerViewCartItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCartItems.adapter = cartAdapter

        // Charger les produits du panier
        val cartItems = CartManager.getCartItems()
        cartAdapter.updateData(cartItems)

        // Afficher le total
        updateTotal()

        // Bouton pour valider la commande
        binding.buttonValidate.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                // Logique pour procéder à la validation de la commande
                Toast.makeText(this, "Commande validée!", Toast.LENGTH_SHORT).show()
                // Ici, tu peux aussi rediriger vers une page de paiement ou confirmer la commande
            } else {
                Toast.makeText(this, "Le panier est vide!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun onCartAction(produit: ProduitResponse, action: String) {
        when (action) {
            "increase" -> CartManager.increaseQuantity(produit)
            "decrease" -> CartManager.decreaseQuantity(produit)
            "delete" -> CartManager.removeFromCart(produit)
        }
        cartAdapter.updateData(CartManager.getCartItems()) // Met à jour les items du panier
        updateTotal() // Met à jour le total après chaque modification
    }

    private fun updateTotal() {
        val total = CartManager.getTotal()
        binding.totalPrice.text = "Total: %.2f €".format(total) // Affiche le total mis à jour
    }




}