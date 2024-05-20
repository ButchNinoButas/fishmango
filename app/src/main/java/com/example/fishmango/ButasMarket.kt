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
                R.id.Settings -> {
                    val intent = Intent(this, ButasSettings::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.Store



        val fishs = listOf(
            Fish("bangus1","Bangus", "₱ 280 per/kg", "Available", R.drawable.banguss),
            Fish("tilapia1","Tilapia", "₱ 300 per/kg", "Available", R.drawable.tilapias),
            Fish("tulignan1","Tulingan", "₱ 250 per/kg", "Available", R.drawable.tulingannew),
            Fish("tamarong1","Tamarong", "₱ 250 per/kg", "Available", R.drawable.tamarong1)
        )


        customAdapter = FishAdapter(this, fishs)
        listView = findViewById(R.id.listViewFish)
        listView.adapter = customAdapter

//        listView.setOnItemClickListener { parent, view, position, id ->
//            val selectedFish = fishs[position]
//
//            val intent = Intent(this@ButasMarket, ButasPlaceOrder::class.java).apply {
//                putExtra("fishId", selectedFish.id)
//                putExtra("fishName", selectedFish.name)
//                putExtra("fishPrice", selectedFish.price)
//                putExtra("fishStatus", selectedFish.status)
//                putExtra("profileImage", selectedFish.profileImage)
//
//
//            }
//
//            startActivity(intent)
//        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedFish = fishs[position]

            val intent = Intent(this@ButasMarket, ButasPlaceOrder::class.java).apply {
                putExtra("fishId", selectedFish.id) // Pass the ID of the clicked fish
                putExtra("fishName", selectedFish.name)
                putExtra("fishPrice", selectedFish.price)
                putExtra("fishStatus", selectedFish.status)
                putExtra("fishImage", selectedFish.profileImage) // Pass the image ID directly
            }

            startActivity(intent)
        }

    }




}