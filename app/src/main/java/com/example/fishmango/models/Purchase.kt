package com.example.fishmango.models

data class Purchase(
    val fishName: String = "",
    val fishPrice: String = "",
    val kilosBought: Double = 0.0,
    val totalAmount: Double = 0.0,
    val location: String = "",
    val landmark: String = "",
    val phoneNumber: String = ""
)

