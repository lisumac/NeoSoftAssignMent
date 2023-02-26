package com.assignment.neosoftassignment.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.responseModel.Thumbnail
import com.assignment.neosoftassignment.model.responseModel.Trailer
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import com.assignment.neosoftassignment.model.retrofit.ApiService
import com.assignment.neosoftassignment.model.roomdataBase.AppDatabase
import com.assignment.neosoftassignment.model.roomdataBase.MovieDao
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MovieDao
) {

    private val _movieList = MutableLiveData<List<MovieResponseItem>>()
    private val error = MutableLiveData<String>()
    val movieResponse: LiveData<List<MovieResponseItem>>
        get() = _movieList

    suspend fun getMovieList() {
        Log.e("TAG", ": Started:::3")
        val result = apiService.getMovieList()
        Log.e("TAG", ": Started:::4$result")
        if (result.isSuccessful && result.body() != null) {
            Log.e("TAG", "getMovieList: " + result.body())

            _movieList.postValue(result.body())

            for (i in 0 until result.body()!!.size) {
                Log.e("TAG", "getMovieList:trail " + result.body()!![i].trailer + "INDEX:::$i")
                if (result.body()!![i].contentRating == null) {
                    result.body()!![i].contentRating = ""
                }

                if (result.body()!![i].trailer == null) {
                    result.body()!![i].trailer = Trailer("", "", "", "", Thumbnail("", ""))
                }

            }
            dao.insertAll(result.body()!!)
            /*dao.insertAll(result.body()!![i]
            _movieList.postValue(result.body()!![i])*/

        } else {
            error.postValue(result.errorBody().toString())
        }
    }

    suspend fun getMovieListFromDataBase() {
        _movieList.postValue(dao.getAll())

    }

}