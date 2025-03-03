package com.example.applie_commerce.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.applie_commerce.R
import com.example.applie_commerce.databinding.ActivityRayonBinding
import com.example.applie_commerce.databinding.ItemRayonBinding

// Data class pour Rayon
data class Rayon(val title: String, val imageResId: Int)

class RayonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRayonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de ViewBinding
        binding = ActivityRayonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuration du RecyclerView avec GridLayout
        binding.recyclerViewRayon.layoutManager = GridLayoutManager(this, 2)

        // Liste des rayons
        val rayonList = listOf(
            Rayon("Électronique", R.drawable.ic_launcher_foreground),
            Rayon("Vêtements", R.drawable.ic_launcher_foreground),
            Rayon("Maison", R.drawable.ic_launcher_foreground),
            Rayon("Sports", R.drawable.ic_launcher_foreground)
        )

        // Adapter intégré dans le même fichier
        val adapter = object : RecyclerView.Adapter<RayonViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RayonViewHolder {
                val itemBinding = ItemRayonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RayonViewHolder(itemBinding)
            }

            override fun onBindViewHolder(holder: RayonViewHolder, position: Int) {
                val rayon = rayonList[position]
                holder.bind(rayon)
            }

            override fun getItemCount(): Int = rayonList.size
        }

        binding.recyclerViewRayon.adapter = adapter
    }

    // ViewHolder intégré
    inner class RayonViewHolder(private val itemBinding: ItemRayonBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(rayon: Rayon) {
            itemBinding.rayonTitle.text = rayon.title
            itemBinding.rayonImage.setImageResource(rayon.imageResId)
        }
    }
}