package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.example.fishmango.databinding.ActivityButasMyProfileFeatureBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ButasMyProfileFeature : AppCompatActivity() {

    private lateinit var binding: ActivityButasMyProfileFeatureBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButasMyProfileFeatureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        usersRef = FirebaseDatabase.getInstance().getReference("users")

        // Populate user data
        val currentUser = auth.currentUser
        currentUser?.let { user ->
            usersRef.child(user.uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val fullName = snapshot.child("fullName").value.toString()
                    val email = snapshot.child("email").value.toString()

                    // Set user data to TextViews
                    binding.FullName.text = fullName
                    binding.email.text = email
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle onCancelled
                }
            })
        }

        // Handle bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.Home -> {
                    startActivity(Intent(this, ButasDashboard::class.java))
                    finish()
                    true
                }
                R.id.Sites -> {
                    startActivity(Intent(this, ButasInsightFeature::class.java))
                    finish()
                    true
                }
                R.id.User -> {
                    true
                }
                R.id.Store -> {
                    startActivity(Intent(this, ButasMarket::class.java))
                    finish()
                    true
                }
                R.id.Settings -> {
                    startActivity(Intent(this, ButasSettings::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
        binding.bottomNavigation.selectedItemId = R.id.User
    }
}
