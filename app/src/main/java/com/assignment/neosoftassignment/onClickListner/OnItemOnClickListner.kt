package com.assignment.neosoftassignment.onClickListner

import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem

interface OnItemOnClickListner {

    fun onItemClickListener(product: MovieResponseItem, position: Int)
    fun navigateToMovieDetails(product: MovieResponseItem, position: Int)
}