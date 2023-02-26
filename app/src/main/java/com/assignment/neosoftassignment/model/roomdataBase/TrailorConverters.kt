package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.Thumbnail
import com.assignment.neosoftassignment.model.responseModel.Trailer
import com.google.gson.Gson

class TrailorConverters {

    @TypeConverter
    fun classToJson(value: Trailer) = if (value != null) {
        Gson().toJson(value)
    } else {
        Gson().toJson("")
    }


    @TypeConverter
    fun jsonToclass(value: String) = if (value != null) {
        Gson().fromJson(value, Trailer::class.java)
    } else {
        Trailer("", "", "", "", Thumbnail("", ""))
    }
}