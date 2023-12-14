package com.thebrownfoxx.push.services

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.thebrownfoxx.push.utils.sendNotification

class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(FirebaseMessagingService::class.java.simpleName, "From: ${message.from}")

        message.data.let {
            Log.d(
                this::class.java.simpleName,
                "Message data payload: " + message.data,
            )
        }

        message.notification?.let {
            Log.d(
                this::class.java.simpleName,
                "Message Notification Body: " + it.body,
            )
            generateNotification(it.body!!, it.title!!)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(FirebaseMessagingService::class.java.simpleName, "My token: $token")
    }


    private fun generateNotification(messageBody: String, messageTitle: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(messageBody, messageTitle, applicationContext)
    }
}