package com.thebrownfoxx.push.ui.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thebrownfoxx.push.extensions.showLongToast
import com.thebrownfoxx.push.extensions.showShortToast
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    private val auth = Firebase.auth

    private val currentUser = auth.currentUser

    private val _navigateUp = MutableSharedFlow<Unit>()
    val navigateUp = _navigateUp.asSharedFlow()

    val email = currentUser?.email ?: "69@69.com"

    fun logout() {
        auth.signOut()
        application.showShortToast("Successfully logged out")
        viewModelScope.launch { _navigateUp.emit(Unit) }
    }
}