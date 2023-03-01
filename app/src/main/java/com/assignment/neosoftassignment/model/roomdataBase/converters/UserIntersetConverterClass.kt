package com.assignment.neosoftassignment.model.roomdataBase.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserIntersetConverterClass {

    @TypeConverter
    fun restoreList(listOfString: String?): ArrayList<String?>? {
        return Gson().fromJson(listOfString, object : TypeToken<ArrayList<String?>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: ArrayList<String?>?): String? {
        return Gson().toJson(listOfString)
    }
}