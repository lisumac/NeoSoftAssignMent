package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.Director
import com.google.gson.Gson

class DirectorConverters {

    @TypeConverter
    fun listToJson(value: List<Director>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Director>::class.java).toList()
}