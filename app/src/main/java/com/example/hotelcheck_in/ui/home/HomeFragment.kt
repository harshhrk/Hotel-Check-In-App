package com.example.hotelcheck_in.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.adpater.HotelAdapter
import com.example.hotelcheck_in.databinding.FragmentHomeBinding
import com.example.hotelcheck_in.listener.RVHotelListener
import com.example.hotelcheck_in.model.HotelModel

class HomeFragment : Fragment(), RVHotelListener {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /** Adapter **/
        val list = ArrayList<HotelModel>()
        list.add(HotelModel(R.drawable.taj, "Taj Hotel", "Spanning world-renowned landmarks, modern business hotels, luxury resorts, authentic heritage palaces and rustic safari lodges"))

        binding.hotelRecyclerView.adapter = HotelAdapter(list, this)
        binding.hotelRecyclerView.layoutManager = LinearLayoutManager(requireActivity())



        return root
    }

    override fun onItemClick(model: HotelModel) {
        val intent = Intent(requireActivity(), HotelDisplay::class.java)
        startActivity(intent)
    }

}