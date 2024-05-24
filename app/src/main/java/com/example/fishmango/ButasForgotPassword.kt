package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ButasForgotPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_forgot_password)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views
        val emailEditText = findViewById<EditText>(R.id.edittextEmail)
        val resetButton = findViewById<Button>(R.id.resetButton)

        // Set OnClickListener to the reset button
        resetButton.setOnClickListener {
            val email = emailEditText.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send password reset email
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, ButasSignIn::class.java))
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
