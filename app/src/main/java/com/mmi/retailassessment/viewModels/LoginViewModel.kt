package com.mmi.retailassessment.viewModels

import android.util.Patterns
import androidx.lifecycle.*
import com.mmi.retailassessment.R
import com.mmi.retailassessment.ui.login.LoginFormState

class LoginViewModel() : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    /**
     * @summary method for validating userid and password using livedata
     * @param string - username, string - password
     * @returns updates livedata loginFormState
     */
    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    /**
     * @summary regex validator for validating username
     * @param string - user name
     * @returns boolean - is user name valid
     */
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    /**
     * @summary length validator for validating password
     * @param string - password
     * @returns boolean - is password valid
     */
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}