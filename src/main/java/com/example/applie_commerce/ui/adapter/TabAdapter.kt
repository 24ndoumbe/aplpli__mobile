package com.example.applie_commerce.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.applie_commerce.ui.MainActivity
import com.example.applie_commerce.ui.fragments.CartFragment
import com.example.applie_commerce.ui.fragments.OrdersFragment
import com.example.applie_commerce.ui.fragments.ProductsFragment
import com.example.applie_commerce.ui.fragments.RayonsFragment

class TabAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4 // Nombre d'onglets

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RayonsFragment()
            1 -> ProductsFragment()
            2 -> CartFragment()
            3 -> OrdersFragment()
            else -> throw IllegalStateException("Position inconnue: $position")
        }
    }
}
