package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.*
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieList")
    fun getAll(): List<MovieResponseItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieResponseItem>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(moviesItemModel: MovieResponseItem)

    @Query("UPDATE movielist SET isFavourite=:isFav WHERE movieResponsePrimaryKey = :id")
    fun update(isFav: Boolean?, id: Int)

    /*  @Query("SELECT * FROM MovieList ORDER BY movieResponsePrimaryKey ASC")
      fun getPagedListMovies(moviesItem: Int, offset: Int):List<MovieResponseItem>*/

    @Query("SELECT * FROM movielist ORDER BY movieResponsePrimaryKey ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int): List<MovieResponseItem>

    @Query("SELECT * FROM movielist WHERE name LIKE :search")
    fun loadHamsters(search: String?): List<MovieResponseItem>
    @Query("SELECT * FROM movielist WHERE (name LIKE :queryString) OR (description LIKE :queryString) ORDER BY movieResponsePrimaryKey  ASC LIMIT :limit OFFSET :offset")
    suspend fun getSearchPagedList(limit: Int, offset: Int, queryString:String): List<MovieResponseItem>
}

