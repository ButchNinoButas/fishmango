package com.example.fishmango.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fishmango.R
import com.example.fishmango.models.Purchase

class PurchaseHistoryAdapter(private val purchaseList: List<Purchase>) : RecyclerView.Adapter<PurchaseHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fishName: TextView = itemView.findViewById(R.id.textViewFishName)
        val fishPrice: TextView = itemView.findViewById(R.id.textViewFishPrice)
        val kilosBought: TextView = itemView.findViewById(R.id.textViewKilosBought)
        val totalAmount: TextView = itemView.findViewById(R.id.textViewTotalAmount)
        val location: TextView = itemView.findViewById(R.id.locationsText)
        val landmark: TextView = itemView.findViewById(R.id.landmarkText)
        val phoneNumber: TextView = itemView.findViewById(R.id.phoneNumberText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_purchase, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = purchaseList[position]
        holder.fishName.text = "Fish Name: ${currentItem.fishName}"
        holder.fishPrice.text = "Fish Price: ${currentItem.fishPrice}"
        holder.kilosBought.text = "Kilos Bought: ${currentItem.kilosBought}"
        holder.totalAmount.text = "Total Amount: ${currentItem.totalAmount}"
        holder.location.text = "Location: ${currentItem.location}"
        holder.landmark.text = "Kilos Bought: ${currentItem.landmark}"
        holder.phoneNumber.text = "Total Amount: ${currentItem.phoneNumber}"
    }

    override fun getItemCount(): Int {
        return purchaseList.size
    }
}
