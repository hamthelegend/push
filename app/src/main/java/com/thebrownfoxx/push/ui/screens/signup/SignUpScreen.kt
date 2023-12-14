package com.thebrownfoxx.push.ui.screens.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thebrownfoxx.components.FilledButton
import com.thebrownfoxx.components.IconButton
import com.thebrownfoxx.components.VerticalSpacer
import com.thebrownfoxx.push.ui.theme.AppTheme

@Composable
fun SignUpScreen(
    email: String,
    onEmailChange: (String) -> Unit,
    emailError: String?,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordError: String?,
    repeatPassword: String,
    onRepeatPasswordChange: (String) -> Unit,
    repeatPasswordError: String?,
    onSignUp: () -> Unit,
    onNavigateUp: () -> Unit,
    signUpButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(16.dp)
            .imePadding()
            .fillMaxSize(),
    ) {
        Row {
            IconButton(
                imageVector = Icons.TwoTone.ArrowBack,
                contentDescription = "Back button",
                onClick = onNavigateUp,
            )
            Text(
                text = "Sign up",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.size(48.dp))
        }
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
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
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
                text = passwordError ?: "",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        VerticalSpacer(height = 16.dp)
        TextField(
            label = { Text(text = "Repeat Password") },
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            isError = repeatPasswordError != null,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(visible = repeatPasswordError != null) {
            Text(
                text = repeatPasswordError ?: "",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp),
            )
        }
        AnimatedVisibility(visible = !signUpButtonEnabled) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )
        }
        VerticalSpacer(height = 16.dp)
        FilledButton(
            text = "Signup",
            onClick = onSignUp,
            enabled = signUpButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    AppTheme {
        SignUpScreen(
            email = "",
            onEmailChange = {},
            emailError = null,
            password = "",
            onPasswordChange = {},
            passwordError = null,
            repeatPassword = "",
            onRepeatPasswordChange = {},
            repeatPasswordError = null,
            onSignUp = {},
            signUpButtonEnabled = true,
            onNavigateUp = {},
        )
    }
}

@Preview
@Composable
fun SignUpScreenErrorPreview() {
    AppTheme {
        SignUpScreen(
            email = "",
            onEmailChange = {},
            emailError = "Email is required",
            password = "",
            onPasswordChange = {},
            passwordError = "Password is required",
            repeatPassword = "",
            onRepeatPasswordChange = {},
            repeatPasswordError = "Passwords do not match",
            onSignUp = {},
            signUpButtonEnabled = true,
            onNavigateUp = {},
        )
    }
}