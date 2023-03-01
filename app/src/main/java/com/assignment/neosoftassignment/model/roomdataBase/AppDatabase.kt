package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.neosoftassignment.model.responseModel.MovieResponseItem
import com.assignment.neosoftassignment.model.roomdataBase.converters.*
import com.assignment.neosoftassignment.model.roomdataBase.dao.MovieDao

@Database(entities = [MovieResponseItem::class], version = 11, exportSchema = false)
@TypeConverters(
    MovieConverters::class, ActorConverters::class,
    AggregateRatingConverters::class,
    CreatorsConverters::class,
    DirectorConverters::class,
    GenreConverters::class,
    TrailorConverters::class, ThumbnailConverter::class
)

abstract class AppDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
}