package com.assignment.neosoftassignment.viewModel

import android.R.array
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.neosoftassignment.Constants
import com.assignment.neosoftassignment.model.repository.MovieRepository
import com.assignment.neosoftassignment.model.repository.RegisterRepository
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val registerRepository: RegisterRepository
) :
    ViewModel() {

    //  lateinit var list_Movies: Flow<PagingData<MovieResponseItem>>
    var searchedList: MutableLiveData<List<MovieResponseItem>>? = null
    var getUserList: MutableLiveData<List<RegisterEntity>>? = null
    var genres :ArrayList<String>? = ArrayList()
    var genres_withoutDuplicates :ArrayList<String>? = ArrayList()

    //var searchedList: MutableLiveData<List<MovieResponseItem>>? = null
    var movieListASCList: MutableLiveData<List<MovieResponseItem>> =
        MutableLiveData<List<MovieResponseItem>>()
    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse

    init {
        Log.e("TAG", ":ViewModelInialized ")
        CoroutineScope(Dispatchers.IO).launch {
            getUserLikeMovie()
            repository.getMovieListFromDataBase()

        }
        searchedList = MutableLiveData<List<MovieResponseItem>>()
        getUserList = MutableLiveData<List<RegisterEntity>>()
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


    fun addToFavList(favourite: Boolean, movieResponsePrimaryKey: Int, genre: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFav(favourite, movieResponsePrimaryKey)
            genres?.addAll(genre)
            for (i in 0 until genres!!.size){
                if (!genres_withoutDuplicates!!.contains(genres!![i])) {
                    genres_withoutDuplicates!!.add(genres!![i])
                }
            }

            registerRepository.addToGenere(genres_withoutDuplicates as ArrayList<String>, Constants.currentUserPrimaryKey)

        }
    }



    fun getSearchList(search: String) {

        val searchValue = repository.getSearchLists(search)
        searchedList?.postValue(searchValue)


    }

    suspend fun getAscOrder(isAsc: Boolean?) {
        val ascOrderListByMovieName = repository.getSortedAscOrder(isAsc)
        movieListASCList?.postValue(ascOrderListByMovieName)
    }

    fun getUserLikeMovie() {
        CoroutineScope(Dispatchers.IO).launch{
            val users = registerRepository.getAllUsers()
            getUserList!!.postValue(users)

        }


    }

}