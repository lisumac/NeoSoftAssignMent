package com.assignment.neosoftassignment.viewModel

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
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    lateinit var flowsearch: Flow<PagingData<MovieResponseItem>>
    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse

    init {
        CoroutineScope(Dispatchers.IO).launch {


            /*val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(true)
                .setPrefetchDistance(20)
                .setPageSize(10).build()
            pagedListLiveData = LivePagedListBuilder(repository.getMoviePagingList(), pagedListConfig).build()*/

            // pagedListLiveData = repository.getElementsLiveData()

        }


    }

   /* val dao = repository.getMoviePagingList()
    val getpagedata = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(dao, "", false)
    }.flow.cachedIn(viewModelScope)*/
    fun addToFavList(favourite: Boolean, movieResponsePrimaryKey: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFav(favourite, movieResponsePrimaryKey)

        }
    }

    fun searchdata(search: String) {

        val dao = repository.getMoviePagingList()
        val getpagedata = Pager(
            PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
        ) {
            if (!search.isNullOrEmpty()){
                MainPagingSource(dao, search, true)
            }else{
                MainPagingSource(dao, "", false)
            }

        }.flow.cachedIn(viewModelScope)
        flowsearch = getpagedata

    }
}