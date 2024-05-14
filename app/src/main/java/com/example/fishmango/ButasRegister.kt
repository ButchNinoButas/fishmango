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

class ButasRegister : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_register)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Find views
        val fullNameEditText = findViewById<EditText>(R.id.editTextFullName)
        val userNameEditText = findViewById<EditText>(R.id.editTextUserName)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextComfirmPassword)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val phoneNumberEditText = findViewById<EditText>(R.id.editTextPhoneNumber)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        // Set OnClickListener to the ButasRegister button
        registerButton.setOnClickListener {
            // Retrieve input values
            val fullName = fullNameEditText.text.toString()
            val userName = userNameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()
            val email = emailEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString()

            // Perform validation
            if (fullName.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                email.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("fullName", fullName)
            editor.putString("username", userName)
            editor.putString("password", password)
            editor.putString("email", email)
            editor.putString("phoneNumber", phoneNumber)
            editor.apply()

            // Display a toast message
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ButasLogin::class.java)
            startActivity(intent)
        }
        val signInTextView = findViewById<TextView>(R.id.SignIntText)


        signInTextView.setOnClickListener {

            val intent = Intent(this@ButasRegister, ButasLogin::class.java)
            startActivity(intent)
        }
    }
}
