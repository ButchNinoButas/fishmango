package com.example.fishmango

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ButasLogin : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_login)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Find views
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText1)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText2)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Set OnClickListener to the login button
        loginButton.setOnClickListener {
            // Retrieve input values
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Authenticate user
            if (authenticateUser(username, password)) {
                // If authentication is successful, start ButasDashboard activity
                val intent = Intent(this@ButasLogin, ButasDashboard::class.java)
                startActivity(intent)
                finish() // Finish the current activity
            } else {
                // If authentication fails, show an error message
                Toast.makeText(this@ButasLogin, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
        // Inside onCreate() method of ButasLogin activity
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        forgotPasswordTextView.setOnClickListener {
            // Handle the click event here
            // For example, navigate to a ForgotPasswordActivity
            // or show a dialog to reset the password
            // For demonstration, let's navigate to a new activity
            val intent = Intent(this, ButasForgotPassword::class.java)
            startActivity(intent)
        }

    }

    // Method to authenticate user using SharedPreferences
    private fun authenticateUser(username: String, password: String): Boolean {
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")


        return username == savedUsername && password == savedPassword
    }
}
