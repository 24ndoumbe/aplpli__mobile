package com.example.applie_commerce.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.applie_commerce.R
import com.example.applie_commerce.api.Rayon
import com.example.applie_commerce.databinding.ItemRayonBinding
import com.example.applie_commerce.ui.ProductActivity

class RayonAdapter(private val rayons: List<Rayon>) : RecyclerView.Adapter<RayonAdapter.RayonViewHolder>() {

    //private lateinit var context: Context
    inner class RayonViewHolder(private val binding: ItemRayonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rayon: Rayon) {
            binding.rayonTitle.text = rayon.libelle


            // Ajouter un gestionnaire de clic sur l'élément du rayon
            itemView.setOnClickListener {
                // Créer un intent pour ouvrir ProductActivity
                val context = itemView.context  // j'utilise context directement depuis itemView
                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra("rayonId", rayon.id) // je passe l'ID du rayon
                context.startActivity(intent) //
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RayonViewHolder {
        val binding = ItemRayonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RayonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RayonViewHolder, position: Int) {
        holder.bind(rayons[position])
    }

    override fun getItemCount(): Int = rayons.size
}

