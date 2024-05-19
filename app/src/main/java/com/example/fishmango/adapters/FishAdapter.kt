package com.example.fishmango.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.fishmango.R
import com.example.fishmango.models.Fish

class FishAdapter(private val context: Context, private val fishs: List<Fish>) : BaseAdapter() {

    override fun getCount(): Int = fishs.size

    override fun getItem(position: Int): Any = fishs[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.fish_list, parent, false)

        val fish = fishs[position]
        val ivProfile: ImageView = view.findViewById(R.id.ivProfile)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)


        // Set data to views
        tvName.text = context.getString(R.string.name_label, fish.name)
        tvPrice.text = context.getString(R.string.price_label, fish.price)
        tvStatus.text = context.getString(R.string.status_label, fish.status)


        ivProfile.setImageResource(fish.profileImage)


        return view
    }
}
