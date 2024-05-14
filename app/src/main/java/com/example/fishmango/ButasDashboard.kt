package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fishmango.databinding.ActivityButasDashboardBinding

class ButasDashboard : AppCompatActivity() {

    private lateinit var binding: ActivityButasDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasDashboardBinding.inflate(layoutInflater)
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


