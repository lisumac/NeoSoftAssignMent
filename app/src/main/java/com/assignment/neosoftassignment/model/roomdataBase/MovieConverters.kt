package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.TypeConverter
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

class MovieConverters {

   var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<MovieResponseItem?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<MovieResponseItem?>?>() {}.type
        return gson.fromJson<List<MovieResponseItem?>>(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<MovieResponseItem?>?): String? {
        return gson.toJson(someObjects)
    }



  /*  @TypeConverter
    fun fromSource(source: MovieResponse): String {
        return JSONObject().apply {
            put("id", source.parentResponsePrimaryKey)
            put("name", source.arraylist)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): MovieResponse {
        val json = JSONObject(source)
        return MovieResponse(json.getInt("id"), json.getJSONArray("name") as ArrayList<MovieResponseItem>)
    }*/
}