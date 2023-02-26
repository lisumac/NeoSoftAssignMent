package com.assignment.neosoftassignment.model.responseModel

import androidx.room.*
import com.assignment.neosoftassignment.model.roomdataBase.GenreConverters
import com.assignment.neosoftassignment.model.roomdataBase.MovieConverters

 class MovieResponse : ArrayList<MovieResponseItem>()


/*
 class MovieResponse(
    @PrimaryKey(autoGenerate = true)
    var parentResponsePrimaryKey: Int,
    // @Ignore
  @TypeConverters(MovieConverters::class)
    //@field:TypeConverters(GenreConverters::class)

    val arraylist: ArrayList<MovieResponseItem>
)
*/
