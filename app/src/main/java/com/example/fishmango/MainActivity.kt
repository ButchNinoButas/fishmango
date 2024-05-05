package com.example.fishmango

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

}
