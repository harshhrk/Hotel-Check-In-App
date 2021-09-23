package com.example.hotelcheck_in.ui.home

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.LocaleData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelcheck_in.R
import com.example.hotelcheck_in.databinding.ActivityBookingBinding
import com.example.hotelcheck_in.model.RoomModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class BookingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model : RoomModel? = intent.getParcelableExtra<RoomModel>("roomData")


        binding.roomName.text = model!!.roomName
        binding.roomPrice.text = "Rs ".plus(model!!.price.toString())
        binding.roomImage.setImageResource(model!!.roomImg)

        binding.payBtn.setOnClickListener {
            val intent = Intent(applicationContext, SuccesfulPaymentActivity::class.java)
            startActivity(intent)
        }


        // Calender
        val calender = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, day)
            val myFormat = "dd-MM-yy" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.checkInUser.editText?.setText(sdf.format(calender.time))
        }

        val date1 = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, month)
            calender.set(Calendar.DAY_OF_MONTH, day)
            val myFormat = "dd-MM-yy" //In which you need put here
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.checkOutUser.editText?.setText(sdf.format(calender.time))


            // updating number of days

            val diff = calculateDate(binding.checkInUser.editText?.text.toString(),
                binding.checkOutUser.editText?.text.toString())
            binding.noOfDays.text = diff.toString().plus(" days")

            // Room Price calculate
            val price = model!!.price * diff
            binding.totalRoomPrice.text = price.toString().plus("/-")
            binding.totalPrice.text = (price + 100 + 150).toString().plus("/-")
        }

        binding.checkInUser.editText?.setOnClickListener {
            DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog,
                date, calender[Calendar.YEAR], calender[Calendar.MONTH], calender[Calendar.DAY_OF_MONTH]).show()
        }

        binding.checkOutUser.editText?.setOnClickListener {
            DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog,
                date1, calender[Calendar.YEAR], calender[Calendar.MONTH], calender[Calendar.DAY_OF_MONTH]).show()
        }


    }


    private fun calculateDate(inputString1 : String, inputString2 : String) : Int {
        val myFormat = "dd-MM-yyyy"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        var days = 0
        try {
            val date1 = simpleDateFormat.parse(inputString1)
            val date2 = simpleDateFormat.parse(inputString2)
            val diff = date2!!.time - date1!!.time
            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return days
    }


}