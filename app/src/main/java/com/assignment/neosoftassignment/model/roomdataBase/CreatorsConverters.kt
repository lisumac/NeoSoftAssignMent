package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.Creator
import com.google.gson.Gson

class CreatorsConverters {

    @TypeConverter
    fun listToJson(value: List<Creator>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Creator>::class.java).toList()
}