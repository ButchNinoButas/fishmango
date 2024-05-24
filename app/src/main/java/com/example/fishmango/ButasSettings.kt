package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fishmango.databinding.ActivityButasSettingsBinding

class ButasSettings : AppCompatActivity() {
    private lateinit var binding: ActivityButasSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasSettingsBinding.inflate(layoutInflater)
        setContentView (binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.Home -> {
                    val intent = Intent(this, ButasDashboard::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.Sites -> {
                    // Handle navigation to My Recipes
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.User -> {
                    val intent = Intent(this, ButasMyProfileFeature::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.Store -> {
                    val intent = Intent(this, ButasMarket::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.Settings -> {

                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.Settings
        val balikImageView = findViewById<ImageView>(R.id.balik)

        // Set OnClickListener to the HvAcct TextView
        balikImageView.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@ButasSettings, ButasDashboard::class.java)
            startActivity(intent)
        }
    }



}