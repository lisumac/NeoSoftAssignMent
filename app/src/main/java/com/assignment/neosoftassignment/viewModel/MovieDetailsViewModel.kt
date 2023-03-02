package com.assignment.neosoftassignment.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assignment.neosoftassignment.model.repository.MovieRepository
import com.assignment.neosoftassignment.model.responseModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    val movieListLiveData: LiveData<List<MovieResponseItem>>
        get() = repository.movieResponse
    var allDataUpdated = false
    lateinit var movieResponseItems: MovieResponseItem


    init {

        CoroutineScope(Dispatchers.IO).launch {

            repository.getMovieListFromDataBase()

        }
        Log.e("MovieDetails", "checkDataFromIntent:343 ")


        movieResponseItems = MovieResponseItem(
            0,
            ArrayList<Actor>(),
            AggregateRating("", 0, 0.0, ""),
            "",
            ArrayList<Creator>(),
            "",
            "",
            ArrayList<Director>(),
            "",
            ArrayList<String>(),
            "",
            "",
            "",
            Trailer("", "", "", "", Thumbnail("", "")),
            "",
            isFavourite = false

        )
    }



    fun insertToDb(favMovieList: ArrayList<MovieResponseItem>) {
        Log.e("TAG", "insertToDb: ", )

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until favMovieList!!.size ) {
                Log.e("TAG", "insertToDb: "+favMovieList[i] )
                if (favMovieList[i].isFavourite) {
                    Log.e("TAG", "insertToDb: "+favMovieList[i] )
                    favMovieList[i].isFavourite= false
                    repository.addToFav(favMovieList[i].isFavourite,favMovieList[i].movieResponsePrimaryKey)
                    allDataUpdated = true
                }
            }

        }


    }

    fun removeORaddToFavList(favourite: Boolean, movieResponsePrimaryKey: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addToFav(favourite, movieResponsePrimaryKey)
        }
    }

    fun getMovieDetailsWithPrimaryKey(movieDetailsPrimaryKey: Int): MovieResponseItem? {
        Log.e("MovieDetails", "hhhh: ")

        //val movieResponseItemTs=MovieResponseItem()
        CoroutineScope(Dispatchers.IO).launch {
            val movieResponseItemDB = repository.getMovieObj(movieDetailsPrimaryKey)
            Log.e("TAG", "jjjj: $movieResponseItemDB")
            movieResponseItems = movieResponseItemDB!!
            Log.e("TAG", "movieResponseItems: $movieResponseItems")
            /*movieResponseItems?.name = movieResponseItemDB?.name!!
            movieResponseItems?.contentRating = movieResponseItemDB?.contentRating!!
            movieResponseItems?.movieResponsePrimaryKey =
                movieResponseItemDB?.movieResponsePrimaryKey!!
            movieResponseItems?.image = movieResponseItemDB?.image!!*/


        }
        return movieResponseItems

    }


}