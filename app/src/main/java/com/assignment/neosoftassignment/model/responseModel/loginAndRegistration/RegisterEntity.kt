package com.assignment.neosoftassignment.model.responseModel.loginAndRegistration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register_users_tables")
data class RegisterEntity(

    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    @ColumnInfo(name = "userName")
    var userName: String,
    @ColumnInfo(name = "email_login")
    var email: String,
    @ColumnInfo(name = "password_text")
    var passwrd: String


)