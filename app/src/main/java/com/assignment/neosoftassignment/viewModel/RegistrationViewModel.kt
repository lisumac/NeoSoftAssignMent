package com.assignment.neosoftassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.neosoftassignment.model.repository.RegisterRepository
import com.assignment.jetpectcompent.utills.FieldValidator.isStringContainNumber
import com.assignment.jetpectcompent.utills.FieldValidator.isStringContainSpecialCharacter
import com.assignment.jetpectcompent.utills.FieldValidator.isStringLowerAndUpperCase
import com.assignment.jetpectcompent.utills.FieldValidator.isValidEmail
import com.assignment.jetpectcompent.utills.toast
import com.assignment.neosoftassignment.model.responseModel.loginAndRegistration.RegisterEntity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: RegisterRepository) : ViewModel() {
     fun validateUserName(userName: TextInputEditText, userNameTextInputLayout: TextInputLayout): Boolean {
        if (userName.text.toString().trim().isEmpty()) {
            userNameTextInputLayout.error = "Required Field!"
            userName.requestFocus()
            return false
        } else {
            userNameTextInputLayout.isErrorEnabled = false
        }
        return true
    }


    fun validateEmail(email: TextInputEditText, emailTextInputLayout: TextInputLayout): Boolean {
        if (email.text.toString().trim().isEmpty()) {
            emailTextInputLayout.error = "Please enter your email"
            email.requestFocus()
            return false
        } else if (!isValidEmail(email.text.toString())) {
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
        } else if (!isStringContainNumber(password.text.toString())) {
            passwordTextInputLayout.error = "Required at least 1 digit"
            password.requestFocus()
            return false
        } else if (!isStringLowerAndUpperCase(password.text.toString())) {
            passwordTextInputLayout.error =
                "Password must contain upper and lower case letters"
            password.requestFocus()
            return false
        } else if (!isStringContainSpecialCharacter(password.text.toString())) {
            passwordTextInputLayout.error = "1 special character required"
            password.requestFocus()
            return false
        } else {
            passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }


     fun validateConfirmPassword(
         confirmPassword: TextInputEditText,
         password: TextInputEditText,
         confirmPasswordTextInputLayout: TextInputLayout
     ): Boolean {
        when {
            confirmPassword.text.toString().trim().isEmpty() -> {
                confirmPasswordTextInputLayout.error = "Required Field!"
                confirmPassword.requestFocus()
                return false
            }
            confirmPassword.text.toString() != password.text.toString() -> {
                confirmPasswordTextInputLayout.error = "Passwords don't match"
                confirmPassword.requestFocus()
                return false
            }
            else -> {
                confirmPasswordTextInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    fun insertUserDetailsToDb(
        userName: TextInputEditText,
        email: TextInputEditText,
        password: TextInputEditText
    ) {
        val userDetailsEntity = RegisterEntity(0,userName.text.toString().trim(),email.text.toString(),password.text.toString())
        viewModelScope.launch {
            delay(1000)
            repository.insert(userDetailsEntity)
        }

    }
}