package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fishmango.databinding.ActivityButasInsightFeatureBinding
import com.example.fishmango.databinding.ActivityButasMyProfileFeatureBinding

class ButasMyProfileFeature : AppCompatActivity() {

    private lateinit var binding: ActivityButasMyProfileFeatureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasMyProfileFeatureBinding.inflate(layoutInflater)
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
                    true
                }

                R.id.Store -> {
                    val intent = Intent(this, ButasMarket::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.Settings -> {
                    val intent = Intent(this, ButasSettings::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.User
    }

}