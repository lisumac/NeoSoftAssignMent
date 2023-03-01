package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.Actor
import com.google.gson.Gson

class ActorConverters {


    @TypeConverter
    fun listToJson(value: List<Actor>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Actor>::class.java).toList()
}