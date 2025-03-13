package com.example.applie_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.applie_commerce.api.ProduitResponse
import com.example.applie_commerce.databinding.ItemProductBinding
import com.example.applie_commerce.ui.CartManager

class ProductAdapter(private var produits: List<ProduitResponse>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = produits[position]
        holder.bind(produit)
    }

    override fun getItemCount(): Int {
        return produits.size
    }

    // Fonction pour mettre à jour les données
    fun updateData(newProduits: List<ProduitResponse>) {
        produits = newProduits
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Ici, bind attend un produit de type ProduitResponse
        fun bind(produit: ProduitResponse) {
            binding.productName.text = "nom : ${produit.nom}"
            binding.productPrice.text = "Prix : ${produit.getPrixAsDouble()} €"
           // binding.productQuantity.text = "Quantité : ${produit.quantite}"

            // Ajout du clic pour ajouter au panier
            binding.addToCartButton.setOnClickListener {
                CartManager.addToCart(produit)
                Toast.makeText(binding.root.context, "${produit.nom} ajouté au panier", Toast.LENGTH_SHORT).show()
            }
        }
    }
}