package com.assignment.neosoftassignment.model.responseModel.roomdataBaseRelation

import androidx.room.Embedded
import androidx.room.Relation
import com.assignment.neosoftassignment.model.responseModel.Director
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem

data class MovieResponseItemAndDirector(
    @Embedded val product: MovieResponseItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "directorPrimaryKey"
    )
    var director: List<Director>? = null,
)

