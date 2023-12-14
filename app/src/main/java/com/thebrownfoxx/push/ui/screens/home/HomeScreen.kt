package com.thebrownfoxx.push.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.components.FilledButton
import com.thebrownfoxx.components.VerticalSpacer
import com.thebrownfoxx.push.ui.theme.AppTheme

@Composable
fun HomeScreen(
    email: String,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp)
            .imePadding()
            .fillMaxSize(),
    ) {
        Text(
            text = "Welcome $email",
            textAlign = TextAlign.Center,
            style = typography.titleLarge,
            modifier = Modifier.fillMaxWidth(),
        )
        VerticalSpacer(height = 16.dp)
        FilledButton(
            text = "Logout",
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.error,
                contentColor = colorScheme.onError,
            ),
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            email = "",
            onLogout = {},
        )
    }
}