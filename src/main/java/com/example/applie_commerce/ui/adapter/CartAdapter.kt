package com.example.applie_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applie_commerce.api.ProduitResponse
import com.example.applie_commerce.databinding.ItemCartBinding

class CartAdapter (
    private var cartItems: List<ProduitResponse>,
    private val onAction: (ProduitResponse, String) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val produit = cartItems[position]
        holder.bind(produit)
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateData(newCartItems: List<ProduitResponse>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(produit: ProduitResponse) {
            binding.cartProductName.text = produit.nom
            binding.cartProductPrice.text = "Prix : ${produit.prix} €"
            binding.cartProductQuantity.text = "Quantité : ${produit.quantite}"

            // Actions
            binding.buttonIncrease.setOnClickListener { onAction(produit, "increase") }
            binding.buttonDecrease.setOnClickListener { onAction(produit, "decrease") }
            binding.buttonDelete.setOnClickListener { onAction(produit, "delete") }
        }
    }
}
