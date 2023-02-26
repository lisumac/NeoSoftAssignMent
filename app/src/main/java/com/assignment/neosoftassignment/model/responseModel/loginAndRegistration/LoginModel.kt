package com.assignment.neosoftassignment.model.responseModel.loginAndRegistration

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class LoginModel(
    @PrimaryKey(autoGenerate = true)
    var userId: Int ,
    @ColumnInfo(name = "userName")
    var userName: String,

    var email: String,

    var passwrd: String)
