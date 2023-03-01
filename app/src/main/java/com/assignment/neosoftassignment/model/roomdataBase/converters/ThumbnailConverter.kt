package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class ThumbnailConverter {
    @TypeConverter
    fun classToJson(value: ThumbnailConverter) = Gson().toJson(value)

    @TypeConverter
    fun jsonToclass(value: String) = Gson().fromJson(value, ThumbnailConverter::class.java)
}