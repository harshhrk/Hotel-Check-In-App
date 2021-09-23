package com.example.hotelcheck_in.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RoomModel (val roomImg : Int, val price : Int, val roomName : String) : Parcelable