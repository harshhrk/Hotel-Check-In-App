package com.example.hotelcheck_in.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.listener.RVRoomListener
import com.example.hotelcheck_in.model.RoomModel

class RoomAdapter(val roomList : ArrayList<RoomModel>, private var listener: RVRoomListener) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roomImageView : ImageView = itemView.findViewById(R.id.roomImage)
        var roomName : TextView = itemView.findViewById(R.id.roomName)
        var roomPrice : TextView = itemView.findViewById(R.id.roomPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sample_room, parent, false))

        view.itemView.setOnClickListener {
            listener.onItemClick(roomList[view.adapterPosition])
        }

        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = roomList[position]
        holder.roomImageView.setImageResource(model.roomImg)
        holder.roomName.text = model.roomName
        holder.roomPrice.text = model.price.toString()
    }

    override fun getItemCount(): Int {
        return roomList.size
    }


}