package com.example.fishmango

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fishmango.databinding.ActivityButasDashboardBinding

class ButasDashboard : AppCompatActivity() {

    private lateinit var binding: ButasDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ButasDashboardBinding.inflate(layoutInflater)
        setContentView (binding.root)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.Home -> {

                    val intent = Intent(this, ButasDashboard::class.java)
                    HomeLauncher.launch(intent)
                    true
                }

                R.id.Sites -> {
                    // Handle navigation to My Recipes
                    val intent = Intent(this, ButasInsightFeature::class.java)
                    startActivity(intent)
                    true
                }

                R.id.User -> {
                    // Handle navigation to Shopping List
                    val intent = Intent(this, ButasMyProfileFeature::class.java)
                    UserLauncher.launch(intent)
                    true
                }

                R.id.Store -> {
                    // Handle navigation to Shopping List
                    val intent = Intent(this, ButasMarket::class.java)
                    SettingsLauncher.launch(intent)
                    true
                }

                else -> false
            }
        }

    }
}


