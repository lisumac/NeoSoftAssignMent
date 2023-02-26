package com.assignment.neosoftassignment.model.retrofit

import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.movieList)
    suspend fun  getMovieList(): Response<MovieResponse>
}