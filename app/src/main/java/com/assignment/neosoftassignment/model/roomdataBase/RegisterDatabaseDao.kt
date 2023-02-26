package com.assignment.neosoftassignment.model.roomdataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity

@Dao
interface RegisterDatabaseDao {
    @Insert
    suspend fun insert(register: RegisterEntity)

    @Query("SELECT * FROM Register_users_tables ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM Register_users_tables WHERE userName LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?

}