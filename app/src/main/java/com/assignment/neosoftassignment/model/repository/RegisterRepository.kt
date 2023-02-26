package com.assignment.neosoftassignment.model.repository

import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import com.assignment.neosoftassignment.model.roomdataBase.RegisterDatabaseDao
import javax.inject.Inject

class RegisterRepository@Inject constructor(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }
}