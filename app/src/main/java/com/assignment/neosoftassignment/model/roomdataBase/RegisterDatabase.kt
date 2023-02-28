package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.neosoftassignment.model.responseModel.MovieResponse
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import javax.inject.Singleton

// autoMigrations = [AutoMigration(from = 1, to = 2)],
@Database(entities = [RegisterEntity::class],
    exportSchema = false,version = 2)
@Singleton
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun registration(): RegisterDatabaseDao


   /* companion object {

        @Volatile
        private var INSTANCE: RegisterDatabase? = null


        fun getInstance(context: Context): RegisterDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }*/




}