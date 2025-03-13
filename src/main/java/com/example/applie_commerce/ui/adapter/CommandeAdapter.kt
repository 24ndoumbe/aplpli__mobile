package com.example.applie_commerce.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applie_commerce.api.Commande
import com.example.applie_commerce.databinding.ItemCommandeBinding


class CommandeAdapter(
    private val commandes: MutableList<Commande>,
    private val onDeleteClick: (Commande) -> Unit
) : RecyclerView.Adapter<CommandeAdapter.CommandeViewHolder>() {

    inner class CommandeViewHolder(private val binding: ItemCommandeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(commande: Commande) {
            binding.textViewLibelle.text = commande.libelle
            binding.textViewQuantite.text = "Quantité: ${commande.quantite}"
            binding.btnDeleteCommande.setOnClickListener { onDeleteClick(commande) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandeViewHolder {
        val binding = ItemCommandeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommandeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommandeViewHolder, position: Int) {
        holder.bind(commandes[position])
    }

    override fun getItemCount() = commandes.size
}
