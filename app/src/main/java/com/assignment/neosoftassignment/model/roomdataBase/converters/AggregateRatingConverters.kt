package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.AggregateRating
import com.google.gson.Gson

class AggregateRatingConverters {

    @TypeConverter
    fun classToJson(value: AggregateRating) = Gson().toJson(value)

    @TypeConverter
    fun jsonToclass(value: String) = Gson().fromJson(value, AggregateRating::class.java)
}