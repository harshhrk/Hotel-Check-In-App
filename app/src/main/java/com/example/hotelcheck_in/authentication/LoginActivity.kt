package com.example.hotelcheck_in.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateOTP.setOnClickListener {
            val phoneNum = binding.phoneNumber.editText?.text.toString()
            if (phoneNum.equals("")) {
                binding.phoneNumber.editText?.setError("This field cannot be empty")
            } else {
                val intent = Intent(applicationContext, VerifiactionActivity::class.java)
                intent.putExtra("phoneNo", phoneNum)
                startActivity(intent)
            }
        }
    }
}