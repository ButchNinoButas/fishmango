package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fishmango.databinding.ActivityButasInsightFeatureBinding
import com.example.fishmango.databinding.ActivityButasMarketBinding
import android.widget.ListView
import com.example.fishmango.adapters.FishAdapter
import com.example.fishmango.models.Fish

class ButasMarket : AppCompatActivity() {
    private lateinit var binding: ActivityButasMarketBinding
    private lateinit var listView: ListView
    private lateinit var customAdapter: FishAdapter
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
        val fishs = listOf(
            Fish("Bangus", "₱ 280", "Available", R.drawable.banguss),
            Fish("Tilapia", "₱ 300", "Available", R.drawable.tilapias),
            Fish("Tulingan", "₱ 250", "Available", R.drawable.tulingannew),
            Fish("Tamarong", "₱ 250", "Available", R.drawable.tamarong1)
        )


        customAdapter = FishAdapter(this, fishs)
        listView = findViewById(R.id.listViewFish)
        listView.adapter = customAdapter
    }




}