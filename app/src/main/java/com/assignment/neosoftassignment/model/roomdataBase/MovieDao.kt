package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.*
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieList")
    fun getAll(): List<MovieResponseItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieResponseItem>)

  /*  @Delete
    fun delete(movie: MovieResponseItem)

    @Delete
    fun deleteAll(movie: List<MovieResponseItem>)*/
}