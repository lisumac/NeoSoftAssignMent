package com.assignment.neosoftassignment.model.roomdataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import com.assignment.neosoftassignment.model.roomdataBase.converters.UserIntersetConverterClass
import com.assignment.neosoftassignment.model.roomdataBase.dao.RegisterDatabaseDao
import javax.inject.Singleton

// autoMigrations = [AutoMigration(from = 1, to = 2)],
@Database(
    entities = [RegisterEntity::class],
    exportSchema = false, version = 3
)
@TypeConverters(UserIntersetConverterClass::class)
@Singleton
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun registration(): RegisterDatabaseDao


}