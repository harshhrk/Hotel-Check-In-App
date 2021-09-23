package com.example.hotelcheck_in.ui.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.authentication.LoginActivity
import com.example.hotelcheck_in.databinding.ActivityHotelDisplayBinding

class HotelDisplay : AppCompatActivity() {

    private lateinit var binding: ActivityHotelDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageResource(R.drawable.taj)

        binding.textView2.text = "The Taj Mahal Palace opened in Mumbai, then Bombay, in 1903, giving birth to the country’s first harbour landmark. The recently trademarked flagship hotel overlooks the majestic Gateway of India. This legendary 5 star hotel in Mumbai has played host to kings, dignitaries and eminent personalities from across the globe, and is acknowledged as a world leader in hospitality. Each of the rooms 285 rooms & suites are a striking blend of nostalgic elegance, rich history and modern facilities."

        binding.button.setOnClickListener {
            val intent = Intent(applicationContext, HotelRoom::class.java)
            startActivity(intent)
        }

    }
}