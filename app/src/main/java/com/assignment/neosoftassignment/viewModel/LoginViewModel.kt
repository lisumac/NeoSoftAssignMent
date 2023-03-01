package com.assignment.neosoftassignment.viewModel

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.neosoftassignment.model.repository.RegisterRepository
import com.assignment.jetpectcompent.utills.FieldValidator
import com.assignment.neosoftassignment.Constants.currentUserName
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: RegisterRepository) : ViewModel(),
    Observable {
    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()
    private val _navigatetoMovieDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoMovieDetails

    fun validateEmail(email: TextInputEditText, emailTextInputLayout: TextInputLayout): Boolean {
        if (email.text.toString().trim().isEmpty()) {
            emailTextInputLayout.error = "Please enter your email"
            email.requestFocus()
            return false
        } else if (!FieldValidator.isValidEmail(email.text.toString())) {
            emailTextInputLayout.error = "Invalid Email!"
            email.requestFocus()
            return false
        } else {
            emailTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun validatePassword(
        password: TextInputEditText,
        passwordTextInputLayout: TextInputLayout
    ): Boolean {
        if (password.text.toString().trim().isEmpty()) {
            passwordTextInputLayout.error = "Please enter your password"
            password.requestFocus()
            return false
        } else if (password.text.toString().length < 6) {
            passwordTextInputLayout.error = "password can't be less than 6"
            password.requestFocus()
            return false
        } else {
            passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    /*fun login() {
       val usersNames = repository.getUserName(inputUsername.value.toString())
       if (usersNames != null) {
           if(usersNames.passwrd == inputPassword.value){
               inputUsername.value = ""
               inputPassword.value = ""
               _navigatetoMovieDetails.value = true
           }else{
             //  _errorToastInvalidPassword.value = true
           }
       } else {
         //  _errorToastUsername.value = true
       }
   }*/
    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("TAG", "login:UserName $email")
            val usersNames = email?.let { repository.getUserName(it) }
            Log.e("TAG", "login:UserNameFromrepos $email")
            if (usersNames != null) {

                currentUserName =usersNames.email
                _navigatetoMovieDetails.postValue(usersNames?.passwrd == password)
            } else {
                _navigatetoMovieDetails.postValue(false)
            }

        }


    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}


