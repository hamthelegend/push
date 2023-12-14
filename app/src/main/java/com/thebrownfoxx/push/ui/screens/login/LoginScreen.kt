package com.thebrownfoxx.push.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.components.FilledButton
import com.thebrownfoxx.components.TextButton
import com.thebrownfoxx.components.VerticalSpacer
import com.thebrownfoxx.push.ui.theme.AppTheme

@Composable
fun LoginScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: String?,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String?,
    onLogin: () -> Unit,
    loginButtonEnabled: Boolean,
    onSignup: () -> Unit,
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
            text = "Login",
            style = typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        VerticalSpacer(height = 32.dp)
        TextField(
            label = { Text(text = "Email") },
            value = email,
            onValueChange = onEmailChange,
            isError = emailError != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(visible = emailError != null) {
            Text(
                text = emailError ?: "",
                style = typography.labelMedium,
                color = colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        VerticalSpacer(height = 16.dp)
        TextField(
            label = { Text(text = "Password") },
            value = password,
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(visible = passwordError != null) {
            Text(
                text = emailError ?: "",
                style = typography.labelMedium,
                color = colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        AnimatedVisibility(visible = !loginButtonEnabled) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )
        }
        VerticalSpacer(height = 16.dp)
        FilledButton(
            text = "Login",
            onClick = onLogin,
            enabled = loginButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
        )
        TextButton(
            text = "Sign up",
            onClick = onSignup,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            email = "",
            onEmailChange = {},
            emailError = null,
            password = "",
            onPasswordChange = {},
            passwordError = null,
            onLogin = {},
            loginButtonEnabled = true,
            onSignup = {},
        )
    }
}

@Preview
@Composable
fun LoginScreenWithErrorPreview() {
    AppTheme {
        LoginScreen(
            email = "",
            onEmailChange = {},
            emailError = "Email is required",
            password = "",
            onPasswordChange = {},
            passwordError = "Password is required",
            onLogin = {},
            loginButtonEnabled = true,
            onSignup = {},
        )
    }
}