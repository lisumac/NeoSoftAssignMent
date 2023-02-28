package com.assignment.neosoftassignment.onClickListner

import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem

interface OnItemOnClickListner {

    fun onItemClickListener(product: MovieResponseItem, position: Int)
}