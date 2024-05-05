package com.example.fishmango

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ButasForgotPassword : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_forgot_password)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Find views
        val emailEditText = findViewById<EditText>(R.id.edittextEmail)
        val newPasswordEditText = findViewById<EditText>(R.id.edittextNewPassword)
        val resetButton = findViewById<Button>(R.id.resetButton)

        resetButton.setOnClickListener {
            // Retrieve the entered email and new password
            val email = emailEditText.text.toString().trim()
            val newPassword = newPasswordEditText.text.toString().trim()

            // Retrieve the saved email from SharedPreferences
            val savedEmail = sharedPreferences.getString("email", "")

            // Check if the entered email matches the saved email
            if (email.isEmpty() || email != savedEmail) {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            } else {
                // Perform password reset (update password in SharedPreferences)
                val editor = sharedPreferences.edit()
                editor.putString("password", newPassword) // Update the password
                editor.apply()

                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show()

                // Finish the activity
                finish()
            }
        }
    }
}
