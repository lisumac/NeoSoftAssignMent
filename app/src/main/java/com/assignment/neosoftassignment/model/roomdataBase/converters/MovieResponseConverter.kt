package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieResponseConverter {

    var gson = Gson()

    @TypeConverter
    fun fromBreakfast(mealItems: List<MovieResponseItem>): String {
        return gson.toJson(mealItems)
    }

    @TypeConverter
    fun toBreakfast(data: String): List<MovieResponse> {
        val listType = object : TypeToken<List<MovieResponseItem>>() {
        }.type
        return gson.fromJson(data, listType)
    }
}