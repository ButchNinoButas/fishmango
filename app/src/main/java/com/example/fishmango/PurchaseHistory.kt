package com.example.fishmango

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fishmango.adapters.PurchaseHistoryAdapter
import com.example.fishmango.models.Purchase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PurchaseHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_history_actvity)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPurchases)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnClearHistory = findViewById<Button>(R.id.buttonClear)
        btnClearHistory.setOnClickListener {
            clearPurchaseHistory()
        }

        loadPurchaseHistory()
    }

    private fun loadPurchaseHistory() {
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
        if (currentUserEmail != null) {
            val usersRef = FirebaseDatabase.getInstance().getReference("users")
            usersRef.orderByChild("email").equalTo(currentUserEmail).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val currentUserKey = snapshot.key
                        if (currentUserKey != null) {
                            val purchasesRef = FirebaseDatabase.getInstance().getReference("users/$currentUserKey/purchases")
                            purchasesRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(purchaseSnapshot: DataSnapshot) {
                                    val purchaseList = ArrayList<Purchase>()
                                    for (purchaseData in purchaseSnapshot.children) {
                                        val purchase = purchaseData.getValue(Purchase::class.java)
                                        if (purchase != null) {
                                            purchaseList.add(purchase)
                                        }
                                    }
                                    val adapter = PurchaseHistoryAdapter(purchaseList)
                                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewPurchases)
                                    recyclerView.adapter = adapter

                                    // Show or hide textViewEmpty based on purchase list size
                                    val textViewEmpty = findViewById<TextView>(R.id.textViewEmpty)
                                    if (purchaseList.isEmpty()) {
                                        textViewEmpty.visibility = View.VISIBLE
                                    } else {
                                        textViewEmpty.visibility = View.GONE
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    // Handle error
                                }
                            })
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        } else {
            Toast.makeText(this, "User email not found", Toast.LENGTH_SHORT).show()
        }
    }


    private fun clearPurchaseHistory() {
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email
        if (currentUserEmail != null) {
            val usersRef = FirebaseDatabase.getInstance().getReference("users")
            usersRef.orderByChild("email").equalTo(currentUserEmail).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val currentUserKey = snapshot.key
                        if (currentUserKey != null) {
                            val purchasesRef = FirebaseDatabase.getInstance().getReference("users/$currentUserKey/purchases")
                            purchasesRef.removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(this@PurchaseHistory, "Purchase history cleared", Toast.LENGTH_SHORT).show()
                                    // Refresh the RecyclerView after clearing the history
                                    loadPurchaseHistory()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this@PurchaseHistory, "Failed to clear purchase history: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@PurchaseHistory, "Failed to clear purchase history: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "User email not found", Toast.LENGTH_SHORT).show()
        }
    }

}
