package com.assignment.neosoftassignment.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.assignment.jetpectcompent.utills.toast
import com.assignment.neosoftassignment.R
import com.assignment.neosoftassignment.databinding.ActivityLoginBinding
import com.assignment.neosoftassignment.onClickListner.OnClickListnerLogin
import com.assignment.neosoftassignment.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), OnClickListnerLogin {
    lateinit var binding: ActivityLoginBinding
    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar!!.hide();
        }
        inIt()
        setupListeners()
        goToMovieList()
    }

    private fun goToMovieList() {
        viewModel.navigatetoUserDetails.observe(this) { hasFinished ->
            if (hasFinished == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                toast(getString(R.string.register_))
            }
        }

    }

    private fun inIt() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.loginVm = viewModel
        binding.handler = this
    }

    private fun setupListeners() {


        binding.email.addTextChangedListener(TextFieldValidation(binding.email))
        binding.password.addTextChangedListener(TextFieldValidation(binding.password))
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            /* checking ids of each text field and applying functions accordingly. */
            when (view.id) {

                R.id.email -> {
                    viewModel.validateEmail(binding.email, binding.emailTextInputLayout)
                }
                R.id.password -> {
                    viewModel.validatePassword(binding.password, binding.passwordTextInputLayout)

                }

            }
        }
    }

    override fun registrationButtonOnclick() {
    }

    override fun loginButtonButtonOnclick() = if (isValidate()) {
        viewModel.login(binding.email.text.toString().trim(), binding.password.text.toString().trim())
    } else {
        this.toast("Please enter proper login details")
    }

    override fun login() {

    }

    private fun isValidate(): Boolean =
        viewModel.validateEmail(
            binding.email,
            binding.emailTextInputLayout
        ) && viewModel.validatePassword(
            binding.password, binding
                .passwordTextInputLayout
        )

    override fun registration() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}