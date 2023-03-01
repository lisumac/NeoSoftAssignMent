package com.assignment.neosoftassignment.model.roomdataBase.dao

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
    suspend fun getAllUsers(): List<RegisterEntity>

    @Query("SELECT * FROM Register_users_tables WHERE email_login LIKE :email ")
    suspend fun getUsername(email: String): RegisterEntity?


    @Query("UPDATE Register_users_tables SET movie_interest=:interest WHERE userId = :id")
   suspend fun updateUserLikes(interest: ArrayList<String>?, id: Int)

    /*@Query("SELECT * FROM Register_users_tables WHERE email_login LIKE :email ")
    suspend fun getPrimaryKey(email: String): RegisterEntity?*/


}