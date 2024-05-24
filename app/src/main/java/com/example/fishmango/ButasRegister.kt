package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase

class ButasRegister : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_butas_register)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Find views
        val fullNameEditText = findViewById<EditText>(R.id.editTextFullName)
        val userNameEditText = findViewById<EditText>(R.id.editTextUserName)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextComfirmPassword)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val phoneNumberEditText = findViewById<EditText>(R.id.editTextPhoneNumber)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        // Set OnClickListener to the Register button
        registerButton.setOnClickListener {
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

            if (!isPasswordValid(password)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters long with at least one number or symbol, and one capital letter.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Register the user with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Registration success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        user?.let {
                            val userId = it.uid
                            val userInfo = User(fullName, userName, email, phoneNumber, rememberMe = false)
                            usersRef.child(userId).setValue(userInfo)
                                .addOnCompleteListener { databaseTask ->
                                    if (databaseTask.isSuccessful) {
                                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this, ButasSignIn::class.java)
                                        finish()
                                        startActivity(intent)
                                    } else {
                                        Toast.makeText(this, "Failed to save user data: ${databaseTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    } else {
                        // If registration fails, display a message to the user.
                        try {
                            throw task.exception!!
                        } catch (weakPassword: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(
                                this,
                                "Password is too weak",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (invalidCredentials: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(
                                this,
                                "Invalid email",
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                this,
                                "Registration failed: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }

        val signInTextView = findViewById<TextView>(R.id.SignIntText)

        signInTextView.setOnClickListener {
            val intent = Intent(this@ButasRegister, ButasSignIn::class.java)
            startActivity(intent)
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[0-9!@#\$%^&*])(?=.*[a-zA-Z]).{8,}$".toRegex()
        return passwordPattern.matches(password)
    }

    data class User(val fullName: String, val userName: String, val email: String, val phoneNumber: String, val rememberMe: Boolean)
}
