package com.example.fishmango

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ButasSignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_sign_in)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        // Find views
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val rememberMeSwitch = findViewById<Switch>(R.id.switch1)

        // Set OnClickListener to the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val rememberMe = rememberMeSwitch.isChecked

            // Perform validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in the user with Firebase Authentication
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update rememberMe field in the database
                        val user = auth.currentUser
                        user?.let {
                            val userId = it.uid
                            usersRef.child(userId).child("rememberMe").setValue(rememberMe)
                                .addOnCompleteListener { databaseTask ->
                                    if (databaseTask.isSuccessful) {
                                        // Store email and password in SharedPreferences if rememberMe is checked
                                        if (rememberMe) {
                                            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                                            val editor = sharedPreferences.edit()
                                            editor.putString("email", email)
                                            editor.putString("password", password)
                                            editor.apply()
                                        }

                                        // Start ButasDashboard activity
                                        Toast.makeText(this, "Sign in successful", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@ButasSignIn, ButasDashboard::class.java)
                                        startActivity(intent)
                                        finish() // Finish the current activity
                                    } else {
                                        Toast.makeText(this, "Failed to update rememberMe: ${databaseTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this, ButasForgotPassword::class.java)
            startActivity(intent)
        }

        val createTextView = findViewById<TextView>(R.id.CreateOneNow)
        createTextView.setOnClickListener {
            val intent = Intent(this@ButasSignIn, ButasRegister::class.java)
            startActivity(intent)
        }
    }
}
