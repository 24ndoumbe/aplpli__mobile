package com.example.applie_commerce.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applie_commerce.R
import com.example.applie_commerce.api.ClientResponse
import com.example.applie_commerce.databinding.ActivityInscriptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_inscription)
        val binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}