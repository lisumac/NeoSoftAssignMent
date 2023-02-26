package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.Embedded
import androidx.room.Relation
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import java.util.*

data class MovieResponseWithListResponse(@Embedded val movieResponse: MovieResponse,@Relation(parentColumn = "parentResponsePrimaryKey",
entityColumn = "parentResponsePrimaryKey")
val movieResponseItem: MovieResponseItem
)




