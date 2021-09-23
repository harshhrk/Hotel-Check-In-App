package com.example.hotelcheck_in.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.adpater.RoomAdapter
import com.example.hotelcheck_in.databinding.ActivityHotelRoomBinding
import com.example.hotelcheck_in.listener.RVRoomListener
import com.example.hotelcheck_in.model.RoomModel

class HotelRoom : AppCompatActivity(), RVRoomListener {

    private lateinit var binding: ActivityHotelRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = com.example.hotelcheck_in.databinding.ActivityHotelRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<RoomModel>()

        list.add(RoomModel(R.drawable.single, 5999, "Single Bed"))
        list.add(RoomModel(R.drawable.twinbed, 10999, "Twin Bed"))
        list.add(RoomModel(R.drawable.deluxe, 15999, "Deluxe Bed"))

        binding.roomRecyclerView.adapter = RoomAdapter(list, this)
        binding.roomRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(model: RoomModel) {
        val intent = Intent(applicationContext, BookingActivity::class.java)
        intent.putExtra("roomData", model)
        startActivity(intent)
    }
}