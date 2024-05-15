package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

                else -> false
            }
        }

        val bangus4Button = findViewById<Button>(R.id.bangus4)


        bangus4Button.setOnClickListener {

            val intent = Intent(this@ButasDashboard, ButasMarket::class.java)
            startActivity(intent)


        }
        val til3Button = findViewById<Button>(R.id.til3)

        // Set OnClickListener to the HvAcct TextView
        til3Button.setOnClickListener {

            val intent = Intent(this@ButasDashboard, ButasMarket::class.java)
            startActivity(intent)
        }
        val till3Button = findViewById<Button>(R.id.till3)

        // Set OnClickListener to SignUpTextView
        till3Button.setOnClickListener {
            // Create Intent to start RegisterActivity
            val intent = Intent(this@ButasDashboard, ButasMarket::class.java)
            startActivity(intent)


        }
        val tilq3Button = findViewById<Button>(R.id.tilq3)


        tilq3Button.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@ButasDashboard, ButasMarket::class.java)
            startActivity(intent)
        }

    }
}


