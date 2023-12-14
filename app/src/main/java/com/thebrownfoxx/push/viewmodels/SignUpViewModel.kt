package com.thebrownfoxx.push.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thebrownfoxx.push.extensions.showLongToast
import com.thebrownfoxx.push.extensions.showShortToast
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val application: Application) : AndroidViewModel(application) {
    private val auth = Firebase.auth

    private val _navigateUp = MutableSharedFlow<Unit>()
    val navigateUp = _navigateUp.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError = _emailError.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError.asStateFlow()

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword = _repeatPassword.asStateFlow()

    private val _repeatPasswordError = MutableStateFlow<String?>(null)
    val repeatPasswordError = _repeatPasswordError.asStateFlow()

    private val _signupButtonEnabled = MutableStateFlow(true)
    val signupButtonEnabled = _signupButtonEnabled.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
        _emailError.value = null
    }

    fun setPassword(password: String) {
        _password.value = password
        _passwordError.value = null
    }

    fun setRepeatPassword(repeatPassword: String) {
        _repeatPassword.value = repeatPassword
        _repeatPasswordError.value = null
    }

    fun signUp() {
        var hasError = false

        val email = email.value
        val password = password.value
        val repeatPassword = repeatPassword.value

        if (email.isBlank()) {
            _emailError.value = "Email is required"
            hasError = true
        }

        if (password.isBlank()) {
            _passwordError.value = "Password is required"
            hasError = true
        } else if (password.length < 8) {
            _passwordError.value = "Password must be at least 8 characters"
            hasError = true
        }

        if (repeatPassword != password) {
            _repeatPasswordError.value = "Passwords do not match"
            hasError = true
        }

        if (!hasError) {
            _signupButtonEnabled.value = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        application.showShortToast("Successfully registered")
                        viewModelScope.launch { _navigateUp.emit(Unit) }
                    }
                }
                .addOnFailureListener { exception ->
                    application.showLongToast("Error: ${exception.localizedMessage}")
                    _signupButtonEnabled.value = true
                }
        }
    }
}