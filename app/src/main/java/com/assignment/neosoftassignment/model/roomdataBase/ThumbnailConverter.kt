package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.AggregateRating
import com.google.gson.Gson

class ThumbnailConverter {
    @TypeConverter
    fun classToJson(value: ThumbnailConverter) = Gson().toJson(value)

    @TypeConverter
    fun jsonToclass(value: String) = Gson().fromJson(value, ThumbnailConverter::class.java)
}