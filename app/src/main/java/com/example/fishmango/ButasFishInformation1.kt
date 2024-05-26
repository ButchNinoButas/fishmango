package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ButasFishInformation1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_fish_information1)
        val balik2ImageView = findViewById<ImageView>(R.id.balik2)


        balik2ImageView.setOnClickListener {

            val intent = Intent(this@ButasFishInformation1, ButasInsightFeature::class.java)
            startActivity(intent)
        }
    }
}