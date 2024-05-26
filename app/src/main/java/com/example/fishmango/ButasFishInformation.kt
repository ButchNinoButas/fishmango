package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ButasFishInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_fish_information)
        val balik2ImageView = findViewById<ImageView>(R.id.balik2)


        balik2ImageView.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@ButasFishInformation, ButasInsightFeature::class.java)
            startActivity(intent)
        }
    }
}