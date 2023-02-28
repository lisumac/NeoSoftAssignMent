package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.assignment.neosoftassignment.onClickListner.OnClickListnerLogin
import com.assignment.jetpectcompent.utills.toast
import com.assignment.neosoftassignment.viewModel.RegistrationViewModel
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity(), OnClickListnerLogin {
    lateinit var binding: ActivityRegistrationBinding
    val viewModel: RegistrationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_registration)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)
        binding.lifecycleOwner = this
        binding.registrationVm = viewModel
        binding.handler = this
        setupListeners()

    }

    private fun setupListeners() {
        binding.userName.addTextChangedListener(TextFieldValidation(binding.userName))
        binding.email.addTextChangedListener(TextFieldValidation(binding.email))
        binding.password.addTextChangedListener(TextFieldValidation(binding.password))
        binding.confirmPassword.addTextChangedListener(TextFieldValidation(binding.confirmPassword))
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            /* checking ids of each text field and applying functions accordingly. */
            when (view.id) {
                R.id.user_name -> viewModel.validateUserName(
                    binding.userName,
                    binding.userNameTextInputLayout
                )
                R.id.email -> {
                    viewModel.validateEmail(binding.email, binding.emailTextInputLayout)
                }
                R.id.password -> {
                    viewModel.validatePassword(binding.password, binding.passwordTextInputLayout)

                }
                R.id.confirm_password ->
                    viewModel.validateConfirmPassword(
                        binding.confirmPassword,
                        binding.password,
                        binding.confirmPasswordTextInputLayout
                    )
            }
        }
    }

    override fun registrationButtonOnclick() {
        if (isValidate()) {
            viewModel.insertUserDetailsToDb(binding.userName,binding.email,binding.password)
            toast("Account Created")
        } else {
            this.toast("Please enter proper login details")
        }
    }

    private fun isValidate(): Boolean =
        viewModel.validateUserName(
            binding.userName,
            binding.userNameTextInputLayout
        ) && viewModel.validateEmail(
            binding.email,
            binding.emailTextInputLayout
        ) && viewModel.validatePassword(
            binding.password, binding
                .passwordTextInputLayout
        ) && viewModel.validateConfirmPassword(
            binding.confirmPassword, binding.password,
            binding.confirmPasswordTextInputLayout
        )

    override fun loginButtonButtonOnclick() {

    }

    override fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun registration() {

    }


}