package com.assignment.neosoftassignment.di

import android.content.Context
import androidx.room.Room
import com.assignment.neosoftassignment.model.roomdataBase.AppDatabase
import com.assignment.neosoftassignment.model.roomdataBase.MovieDao
import com.assignment.neosoftassignment.model.roomdataBase.RegisterDatabase
import com.assignment.neosoftassignment.model.roomdataBase.RegisterDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

   @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "moveApp.db"
        ).build()
    }

    @Provides
    fun provideMovieListDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun provideRegistrationDatabase(@ApplicationContext appContext: Context): RegisterDatabase {
        return Room.databaseBuilder(
            appContext,
            RegisterDatabase::class.java,
            "user_details_database.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(appDatabase: RegisterDatabase): RegisterDatabaseDao {
        return appDatabase.registration()
    }
}