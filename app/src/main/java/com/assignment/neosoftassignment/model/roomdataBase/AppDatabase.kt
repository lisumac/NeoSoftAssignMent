package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.responseModel.Trailer
import javax.inject.Singleton

@Database(entities = [MovieResponseItem::class], version = 10, exportSchema = false)
@TypeConverters(MovieConverters::class, ActorConverters::class,AggregateRatingConverters::class,CreatorsConverters::class,DirectorConverters::class,GenreConverters::class,
    TrailorConverters::class,ThumbnailConverter::class
)

abstract class AppDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
}