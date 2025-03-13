package com.example.applie_commerce.ui

import com.example.applie_commerce.api.ProduitResponse

object CartManager {
    private val cartItems = mutableListOf<ProduitResponse>()

    fun getCartItems(): List<ProduitResponse> = cartItems

    fun addToCart(produit: ProduitResponse) {
        val existingItem = cartItems.find { it.id == produit.id }
        if (existingItem != null) {
            existingItem.quantite += 1
        } else {
            cartItems.add(ProduitResponse(produit.id, produit.nom, produit.prix,produit.description, produit.lieu, produit.fkRayon,1)) // Ajoute avec quantité = 1
        }
    }

    fun removeFromCart(produit: ProduitResponse) {
        cartItems.remove(produit)
    }

    fun increaseQuantity(produit: ProduitResponse) {
        val existingItem = cartItems.find { it.id == produit.id }
        existingItem?.quantite = (existingItem?.quantite ?: 1) + 1
    }

    fun decreaseQuantity(produit: ProduitResponse) {
        val existingItem = cartItems.find { it.id == produit.id }
        if (existingItem != null && existingItem.quantite > 1) {
            existingItem.quantite -= 1
        } else {
            removeFromCart(produit)
        }
    }
    // Ajouter la méthode getTotal qui calcule le total du panier
    fun getTotal(): Double {
        var total = 0.0
        for (item in cartItems) {
            total += item.prix.toDouble() * item.quantite
        }
        return total
    }
}