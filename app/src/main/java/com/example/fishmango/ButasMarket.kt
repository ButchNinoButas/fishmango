package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fishmango.databinding.ActivityButasInsightFeatureBinding
import com.example.fishmango.databinding.ActivityButasMarketBinding

class ButasMarket : AppCompatActivity() {
    private lateinit var binding: ActivityButasMarketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasMarketBinding.inflate(layoutInflater)
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

                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.Store
    }

}