package com.assignment.neosoftassignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.assignment.neosoftassignment.model.repository.MovieRepository
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.pagination.MainPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

      lateinit var list_Movies: Flow<PagingData<MovieResponseItem>>
    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse

    init {
        Log.e("TAG", ":ViewModelInialized " )
        CoroutineScope(Dispatchers.IO).launch {
            searchdata("")
        }

     /*  val dao = repository.getMoviePagingList()
        val getpagedata = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
        ) {
            MainPagingSource(dao, "", true)
        }.flow.cachedIn(viewModelScope)
        list_Movies = getpagedata*/
    }


    fun addToFavList(favourite: Boolean, movieResponsePrimaryKey: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFav(favourite, movieResponsePrimaryKey)

        }
    }

    suspend  fun  searchdata(search: String) {
        Log.e("TAG", "search-data: $search")

           /* val dao = repository.getMoviePagingList()
            val getpagedata = Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                ),
            ) {
                Log.e("TAG", "paged: $search")
                if (search.isNullOrEmpty()){
                    Log.e("TAG", "list_Movies: $search")
                    MainPagingSource(dao, "", false)
                }else{
                    Log.e("TAG", "list_Movies: $search")
                    MainPagingSource(dao, search, true)

                }

            }.flow.cachedIn(viewModelScope)*/

          // list_Movies = getpagedata
        Log.e("TAG", "list_Movies:rgh ::::"+list_Movies )




    }
    fun getSearchList(){
        repository.getSearchList("The Kid")
    }
}