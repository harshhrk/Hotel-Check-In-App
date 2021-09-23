package com.example.hotelcheck_in.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.listener.RVHotelListener
import com.example.hotelcheck_in.model.HotelModel

class HotelAdapter (val hotelList : ArrayList<HotelModel>, private val listener: RVHotelListener) : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelImage : ImageView = itemView.findViewById(R.id.hotelImage)
        val hotelName : TextView = itemView.findViewById(R.id.hotelName)
        val hotelDesc : TextView = itemView.findViewById(R.id.hotelDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_hotel, parent, false));

        view.itemView.setOnClickListener {
            listener.onItemClick(hotelList[view.adapterPosition])
        }

        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = hotelList[position]
        holder.hotelImage.setImageResource(model.hotelImg)
        holder.hotelName.text = model.hotelName
        holder.hotelDesc.text = model.hotelDesc
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }


}