package com.assignment.neosoftassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.neosoftassignment.model.repository.MovieRepository
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteMovieListViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {
    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse
    init {

        CoroutineScope(Dispatchers.IO).launch {

            repository.getMovieListFromDataBase()
        }
    }

    fun removeFromFavList(favourite: Boolean, movieResponsePrimaryKey: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFav(favourite, movieResponsePrimaryKey)
        }
    }



}