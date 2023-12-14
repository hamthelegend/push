package com.thebrownfoxx.push.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import com.thebrownfoxx.push.extensions.application
import com.thebrownfoxx.push.ui.screens.destinations.LoginDestination

@Destination
@Composable
fun Home(navigator: DestinationsNavigator) {
    val viewModel = viewModel { HomeViewModel(application) }

    BackHandler {
        viewModel.logout()
    }

    LaunchedEffect(viewModel.navigateUp) {
        viewModel.navigateUp.collect {
            navigator.navigateUp()
        }
    }

    HomeScreen(
        email = viewModel.email,
        onLogout = viewModel::logout,
    )
}