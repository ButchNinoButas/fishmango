package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ButasInsightFeature : AppCompatActivity() {
    private lateinit var binding: ButasInsightFeature
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_insight_feature)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Find SignUpTextView by id
        val homeImageButton = findViewById<ImageButton>(R.id.Home)

        // Set OnClickListener to SignUpTextView
        homeImageButton.setOnClickListener {
            // Create Intent to start RegisterActivity
            val intent = Intent(this@ButasInsightFeature, ButasDashboard::class.java)
            startActivity(intent)


        }
        val userImageButton= findViewById<ImageButton>(R.id.User)

        // Set OnClickListener to the HvAcct TextView
        userImageButton.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@ButasInsightFeature, ButasMyProfileFeature::class.java)
            startActivity(intent)
        }
    }
}