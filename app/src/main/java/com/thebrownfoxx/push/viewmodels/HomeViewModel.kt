package com.thebrownfoxx.push.viewmodels

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.thebrownfoxx.push.extensions.showLongToast
import com.thebrownfoxx.push.extensions.showShortToast
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val application: Application) : AndroidViewModel(application) {
    private val auth = Firebase.auth
    private val messaging = Firebase.messaging

    private val currentUser = auth.currentUser

    private val _navigateUp = MutableSharedFlow<Unit>()
    val navigateUp = _navigateUp.asSharedFlow()

    val email = currentUser?.email ?: "69@69.com"

    init {
        messaging.token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d(this::class.java.simpleName, "Token: $token")
                application.showShortToast("Token Received")
            } else {
                application.showLongToast("Fetching FCM registration token failed: ${task.exception}")
            }
        }

        createChannel()
        subscribeToTopic()
    }

    fun logout() {
        auth.signOut()
        application.showShortToast("Successfully logged out")
        viewModelScope.launch { _navigateUp.emit(Unit) }
    }

    private fun createChannel() {
        val notificationChannel = NotificationChannel(
            "main_channel",
            "Main",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            setShowBadge(false)
        }

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Main Channel"

        val notificationManager = application.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("main")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    application.showShortToast("Subscribed to topic")
                } else {
                    application.showLongToast("Unable to subscribe to topic: ${task.exception?.localizedMessage}")
                    return@addOnCompleteListener
                }
            }
    }
}