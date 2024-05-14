package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fishmango.databinding.ActivityButasDashboardBinding
import com.example.fishmango.databinding.ActivityButasInsightFeatureBinding

class ButasInsightFeature : AppCompatActivity() {
    private lateinit var binding: ActivityButasInsightFeatureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasInsightFeatureBinding.inflate(layoutInflater)
        setContentView (binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.Home -> {
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    true
                }

                R.id.Sites -> {
                    // Handle navigation to My Recipes
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    true
                }

                R.id.User -> {
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    true
                }

                R.id.Store -> {
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}