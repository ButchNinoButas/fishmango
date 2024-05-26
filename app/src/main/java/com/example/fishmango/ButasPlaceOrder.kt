package com.example.fishmango

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ButasPlaceOrder : AppCompatActivity() {




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_butas_place_order)

        // Retrieve data from Intent
        val fishName = intent.getStringExtra("fishName")
        val fishPrice = intent.getStringExtra("fishPrice")
        val fishStatus = intent.getStringExtra("fishStatus")

//        val fishImage = intent.getIntExtra("fishImage", R.drawable.banguss)
        val fishImage = intent.getIntExtra("fishImage", R.drawable.banguss)

        // Populate TextViews with received data
        findViewById<TextView>(R.id.FishName).text = "Name: $fishName"
        findViewById<TextView>(R.id.FishPrice).text = "Price: $fishPrice"
        findViewById<TextView>(R.id.FishStatus).text = "Status: $fishStatus"

        findViewById<ImageView>(R.id.FishProfile).setImageResource(fishImage)
        val cancelButton = findViewById<Button>(R.id.backButtons)

        // Set OnClickListener to the purchase button
        val purchaseButton = findViewById<Button>(R.id.purchaseButton)
        purchaseButton.setOnClickListener {
            showPurchaseDialog(fishName ?: "", fishPrice ?: "")
        }


        // Set OnClickListener to the HvAcct TextView
        cancelButton.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this@ButasPlaceOrder, ButasMarket::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showPurchaseDialog(fishName: String, fishPrice: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_place_order, null)
        val etKilos = dialogView.findViewById<EditText>(R.id.etKilos)
        val etLocation = dialogView.findViewById<EditText>(R.id.location)
        val etLandmark = dialogView.findViewById<EditText>(R.id.landmark)
        val etPhoneNumber = dialogView.findViewById<EditText>(R.id.phonenumber)

        AlertDialog.Builder(this)
            .setTitle("Purchase $fishName")
            .setView(dialogView)
            .setPositiveButton("Purchase") { _: DialogInterface, _: Int ->
                val kilos = etKilos.text.toString().toDoubleOrNull()
                val location = etLocation.text.toString()
                val landmark = etLandmark.text.toString()
                val phoneNumber = etPhoneNumber.text.toString()

                if (landmark.isEmpty()) {
                    Toast.makeText(this, "Enter the landmark", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (location.isEmpty()) {
                    Toast.makeText(this, "Enter the location", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(this, "Enter the phone number", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (kilos != null && kilos > 0) {
                    // Extract the numeric part of the fish price
                    val numericFishPrice = fishPrice.replace(Regex("[^\\d.]"), "")
                    val totalAmount = kilos * numericFishPrice.toDouble()

                    // Retrieve the current user's key
                    val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
                    if (currentUserEmail != null) {
                        val usersRef = FirebaseDatabase.getInstance().getReference("users")
                        usersRef.orderByChild("email").equalTo(currentUserEmail).addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                for (snapshot in dataSnapshot.children) {
                                    val currentUserKey = snapshot.key
                                    if (currentUserKey != null) {
                                        // Create a reference to the purchases node for the user
                                        val purchasesRef = FirebaseDatabase.getInstance().getReference("users/$currentUserKey/purchases").push()

                                        // Create a Purchase object
                                        val purchase = HashMap<String, Any>()
                                        purchase["fishName"] = fishName
                                        purchase["fishPrice"] = fishPrice
                                        purchase["kilosBought"] = kilos
                                        purchase["totalAmount"] = totalAmount
                                        purchase["location"] = location
                                        purchase["landmark"] = landmark
                                        purchase["phoneNumber"] = phoneNumber

                                        // Save the purchase to the database
                                        purchasesRef.setValue(purchase)
                                            .addOnSuccessListener {
                                                Toast.makeText(this@ButasPlaceOrder, "Purchase successful", Toast.LENGTH_SHORT).show()
                                            }
                                            .addOnFailureListener {
                                                Toast.makeText(this@ButasPlaceOrder, "Failed to purchase", Toast.LENGTH_SHORT).show()
                                            }
                                    } else {
                                        Toast.makeText(this@ButasPlaceOrder, "User not found", Toast.LENGTH_SHORT).show()
                                    }
                                    return
                                }
                                // If no user was found
                                Toast.makeText(this@ButasPlaceOrder, "User not found", Toast.LENGTH_SHORT).show()
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                // Handle error
                                Toast.makeText(this@ButasPlaceOrder, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    } else {
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Please enter a valid number of kilos", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }




}
