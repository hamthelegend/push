package com.thebrownfoxx.push.ui.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thebrownfoxx.push.extensions.application
import com.thebrownfoxx.push.viewmodels.SignUpViewModel

@Destination
@Composable
fun SignUp(navigator: DestinationsNavigator) {
    val viewModel = viewModel { SignUpViewModel(application) }

    val email by viewModel.email.collectAsStateWithLifecycle()
    val emailError by viewModel.emailError.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()
    val repeatPassword by viewModel.repeatPassword.collectAsStateWithLifecycle()
    val repeatPasswordError by viewModel.repeatPasswordError.collectAsStateWithLifecycle()
    val signupButtonEnabled by viewModel.signupButtonEnabled.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.navigateUp) {
        viewModel.navigateUp.collect {
            navigator.navigateUp()
        }
    }

    SignUpScreen(
        email = email,
        emailError = emailError,
        password = password,
        passwordError = passwordError,
        repeatPassword = repeatPassword,
        repeatPasswordError = repeatPasswordError,
        onEmailChange = viewModel::setEmail,
        onPasswordChange = viewModel::setPassword,
        onRepeatPasswordChange = viewModel::setRepeatPassword,
        onSignUp = viewModel::signUp,
        signUpButtonEnabled = signupButtonEnabled,
        onNavigateUp = { navigator.navigateUp() },
    )
}