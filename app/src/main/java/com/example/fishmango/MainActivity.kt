package com.example.fishmango

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        // Create and show the loading dialog
        showLoadingDialog()

        // Check if the user is already signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in, check if rememberMe is true
            val userId = currentUser.uid
            usersRef.child(userId).child("rememberMe").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val rememberMe = snapshot.getValue(Boolean::class.java) ?: false
                    if (rememberMe) {
                        // Remember me is true, redirect to Dashboard
                        val intent = Intent(this@MainActivity, ButasDashboard::class.java)
                        startActivity(intent)
                        finish() // Finish the current activity
                    } else {
                        // Remember me is false, stay on this activity
                        setupUI()
                    }
                    dismissLoadingDialog()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle possible errors.
                    setupUI()
                    dismissLoadingDialog()
                }
            })
        } else {
            // User is not signed in, stay on this activity
            setupUI()
            dismissLoadingDialog()
        }
    }

    private fun setupUI() {
        // Find SignUpTextView by id
        val signUpTextView = findViewById<TextView>(R.id.SignUpTextView)

        // Set OnClickListener to SignUpTextView
        signUpTextView.setOnClickListener {
            // Create Intent to start RegisterActivity
            val intent = Intent(this@MainActivity, ButasRegister::class.java)
            startActivity(intent)
        }

        val hvAcctTextView = findViewById<TextView>(R.id.HvAcct)

        // Set OnClickListener to the HvAcct TextView
        hvAcctTextView.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@MainActivity, ButasSignIn::class.java)
            startActivity(intent)
        }
    }

    private fun showLoadingDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_loading, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        loadingDialog = builder.create()
        loadingDialog.show()
    }

    private fun dismissLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}
