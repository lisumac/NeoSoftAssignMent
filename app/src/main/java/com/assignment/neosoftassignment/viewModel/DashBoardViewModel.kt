package com.assignment.neosoftassignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assignment.neosoftassignment.model.repository.MovieRepository
import com.assignment.neosoftassignment.model.repository.RegisterRepository
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse

    init {
        CoroutineScope(Dispatchers.IO).launch {
            kotlinx.coroutines.delay(100)
            repository.getMovieListFromDataBase()
        }
    }

}