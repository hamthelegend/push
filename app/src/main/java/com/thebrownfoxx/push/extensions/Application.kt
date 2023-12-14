package com.thebrownfoxx.push.extensions

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

val CreationExtras.application
    get() = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!)