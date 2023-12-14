package com.thebrownfoxx.push.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.thebrownfoxx.push.extensions.application
import com.thebrownfoxx.push.ui.screens.destinations.HomeDestination
import com.thebrownfoxx.push.ui.screens.destinations.SignUpDestination

@RootNavGraph(start = true)
@Destination
@Composable
fun Login(navigator: DestinationsNavigator) {
    val viewModel = viewModel { LoginViewModel(application) }

    val email by viewModel.email.collectAsStateWithLifecycle()
    val emailError by viewModel.emailError.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()
    val loginButtonEnabled by viewModel.loginButtonEnabled.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.login.collect {
            navigator.navigate(HomeDestination)
        }
    }

    LoginScreen(
        email = email,
        emailError = emailError,
        password = password,
        passwordError = passwordError,
        onEmailChange = viewModel::setEmail,
        onPasswordChange = viewModel::setPassword,
        onLogin = viewModel::login,
        loginButtonEnabled = loginButtonEnabled,
        onSignup = { navigator.navigate(SignUpDestination) },
    )
}