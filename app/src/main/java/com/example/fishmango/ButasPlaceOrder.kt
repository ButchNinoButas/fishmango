package com.example.fishmango

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ButasPlaceOrder : AppCompatActivity() {




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_place_order)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // Retrieve data from Intent
        val fishName = intent.getStringExtra("fishName")
        val fishPrice = intent.getStringExtra("fishPrice")
        val fishStatus = intent.getStringExtra("fishStatus")

        val fishImage = intent.getIntExtra("fishImage", R.drawable.banguss)

        // Populate TextViews with received data
        findViewById<TextView>(R.id.FishName).text = "Name: $fishName"
        findViewById<TextView>(R.id.FishPrice).text = "Price: $fishPrice"
        findViewById<TextView>(R.id.FishStatus).text = "Status: $fishStatus"

        findViewById<ImageView>(R.id.FishProfile).setImageResource(fishImage)
    }
}