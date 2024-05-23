package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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
                    val intent = Intent(this, ButasDashboard::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                R.id.Sites -> {

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
                    val intent = Intent(this, ButasSettings::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.Sites

        val viewDetailsButton: AppCompatButton = findViewById(R.id.viewdetails1)
        viewDetailsButton.setOnClickListener {
            val intent = Intent(this@ButasInsightFeature, ButasFishInformation::class.java)
            startActivity(intent)
        }
        val bangusbtn1Button = findViewById<Button>(R.id.bangusbtn1)
        bangusbtn1Button.setOnClickListener {
            val intent = Intent(this@ButasInsightFeature, ButasFishInformation1::class.java)
            startActivity(intent)
        }
        val tamarongbtnButton = findViewById<Button>(R.id.tamarongbtn)
        tamarongbtnButton.setOnClickListener {
            val intent = Intent(this@ButasInsightFeature, ButasFishInformation2::class.java)
            startActivity(intent)
        }
        val tulibtnButton = findViewById<Button>(R.id.tulibtn)
        tulibtnButton.setOnClickListener {
            val intent = Intent(this@ButasInsightFeature, ButasFishInformation3::class.java)
            startActivity(intent)
        }

    }

}