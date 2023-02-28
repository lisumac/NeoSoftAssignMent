package com.assignment.neosoftassignment.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.assignment.neosoftassignment.model.pagination.MoviesPagingSource

import com.assignment.neosoftassignment.model.responseModel.Creator
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.responseModel.Thumbnail
import com.assignment.neosoftassignment.model.responseModel.Trailer
import com.assignment.neosoftassignment.model.retrofit.ApiService
import com.assignment.neosoftassignment.model.roomdataBase.MovieDao
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: MovieDao
) {

    var pagedListLiveData: LiveData<PagedList<MovieResponseItem>>? = null
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
            dao.insertAll(result.body()!!)


        } else {
            error.postValue(result.errorBody().toString())
        }
    }

    suspend fun getMovieListFromDataBase() {
        _movieList.postValue(dao.getAll())

    }



    fun getMoviePagingList() :MovieDao{
     return dao
    }

  /*  fun getElementsLiveData(): LiveData<PagedList<MovieResponseItem>> {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .setPrefetchDistance(2).build()

        // val data = dao.getAllMovies()
//        return LivePagedListBuilder(data, 10).build() // 10 is page size
       // return LivePagedListBuilder(dao.getAllMovies(), pagedListConfig).build()

    }
*/

    suspend fun addToFav(isFav: Boolean, id: Int) {
        dao.update(isFav, id)

    }

}