package com.assignment.neosoftassignment.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.assignment.neosoftassignment.model.responseModel.Creator
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.responseModel.Thumbnail
import com.assignment.neosoftassignment.model.responseModel.Trailer
import com.assignment.neosoftassignment.model.retrofit.ApiService
import com.assignment.neosoftassignment.model.roomdataBase.dao.MovieDao
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MovieDao
) {

    private val _movieList = MutableLiveData<List<MovieResponseItem>>()
    private val searched_movie_List = MutableLiveData<List<MovieResponseItem>>()
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
                Log.e("TAG", "getMovieList:creater " + result.body()!![i].creator + "INDEX:::$i")
                if (result.body()!![i].contentRating == null) {
                    result.body()!![i].contentRating = ""
                }


                if (result.body()!![i].trailer == null) {
                    result.body()!![i].trailer = Trailer("", "", "", "", Thumbnail("", ""))
                }
                for (j in 0 until result.body()!![i].creator.size - 1) {
                    if (result.body()!![i].creator[j].name == null) {
                        val creator = Creator(
                            result.body()!![i].creator[j].type, "",
                            result.body()!![i].creator[j].url
                        )
                        result.body()!![i].creator.toMutableList().add(j, creator)
                    }
                }

            }
            if (dao.getAll()!!.isNullOrEmpty()) {
                dao.insertAll(result.body()!!)
            }


        } else {
            error.postValue(result.errorBody().toString())
        }
    }

    suspend fun getMovieListFromDataBase() {
        _movieList.postValue(dao.getAll())

    }


    fun getSearchLists(search: String): List<MovieResponseItem> {
        Log.e("TAG", "setUpRecyclerview: " + dao.getSearch(search))
        Log.e("TAG", "setUpRecyclerview: $search")

        return dao.getSearch(search)
    }

    fun getMoviePagingList(): MovieDao {
        return dao
    }


    suspend fun addToFav(isFav: Boolean, id: Int) {
        dao.update(isFav, id)

    }


    suspend fun getSortedAscOrder(isAsc: Boolean?): List<MovieResponseItem> {
      return  dao.getSortedListByName(isAsc)
    }


}