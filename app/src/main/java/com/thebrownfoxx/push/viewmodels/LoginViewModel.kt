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

class LoginViewModel(private val application: Application) : AndroidViewModel(application) {
    private val auth = Firebase.auth

    private val _login = MutableSharedFlow<Unit>()
    val login = _login.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError = _emailError.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError.asStateFlow()

    private val _loginButtonEnabled = MutableStateFlow(true)
    val loginButtonEnabled = _loginButtonEnabled.asStateFlow()

    fun setEmail(email: String) {
        _email.value = email
        _emailError.value = null
    }

    fun setPassword(password: String) {
        _password.value = password
        _passwordError.value = null
    }

    fun login() {
        var hasError = false

        val email = email.value
        val password = password.value

        if (email.isBlank()) {
            _emailError.value = "Required"
            hasError = true
        }

        if (password.isBlank()) {
            _passwordError.value = "Required"
            hasError = true
        }

        if (!hasError) {
            _loginButtonEnabled.value = false
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        application.showShortToast("Successfully logged in")
                        viewModelScope.launch { _login.emit(Unit) }
                        _loginButtonEnabled.value = true
                    }
                }
                .addOnFailureListener { exception ->
                    application.showLongToast("Error: ${exception.localizedMessage}")
                    _loginButtonEnabled.value = true
                }
        }
    }
}