package com.example.applie_commerce.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applie_commerce.api.Produit
import com.example.applie_commerce.databinding.ItemOrderProductBinding

class OrderDetailsAdapter (private val products: List<Produit>) :
    RecyclerView.Adapter<OrderDetailsAdapter.OrderProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderProductViewHolder {
        val binding = ItemOrderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    inner class OrderProductViewHolder(private val binding: ItemOrderProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Produit) {
            binding.productNom.text = product.nom
            binding.productPrix.text = "Prix : ${product.prix} €"
            //binding.productQuantity.text = "Quantité : ${product.quantite}"
        }
    }
}