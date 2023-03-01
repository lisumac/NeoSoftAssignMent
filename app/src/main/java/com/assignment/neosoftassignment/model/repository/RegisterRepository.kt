package com.assignment.neosoftassignment.model.repository

import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import com.assignment.neosoftassignment.model.roomdataBase.dao.RegisterDatabaseDao
import javax.inject.Inject

class RegisterRepository@Inject constructor(private val dao: RegisterDatabaseDao) {

   // val users = dao.getAllUsers()

    suspend fun getAllUsers(): List<RegisterEntity>{
        return dao.getAllUsers()
    }
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserName(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }
    suspend fun addToGenere(interest: ArrayList<String>?, id: Int) {
        return dao.updateUserLikes(interest, id = id)
    }
}