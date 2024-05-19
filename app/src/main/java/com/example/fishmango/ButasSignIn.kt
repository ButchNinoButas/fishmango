package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ButasSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_sign_in)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val signInButton = findViewById<Button>(R.id.loginButton)
        signInButton.setOnClickListener{
            val intent = Intent(this@ButasSignIn, ButasDashboard::class.java)
            startActivity(intent)

        }
        val createTextView = findViewById<TextView>(R.id.CreateOneNow)
        createTextView.setOnClickListener {
            val intent = Intent(this@ButasSignIn, ButasRegister::class.java)
            startActivity(intent)
        }
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        forgotPasswordTextView.setOnClickListener {
            val intent = Intent(this@ButasSignIn, ButasForgotPassword::class.java)
            startActivity(intent)
        }


    }
}