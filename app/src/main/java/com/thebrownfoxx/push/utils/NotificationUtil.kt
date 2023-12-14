package com.thebrownfoxx.push.utils

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.thebrownfoxx.push.MainActivity
import com.thebrownfoxx.push.R

private const val NOTIFICATION_ID = 6969

@SuppressLint("UnspecifiedImmutableFlag")
fun NotificationManager.sendNotification(messageBody: String, messageTitle: String, applicationContext: Context){
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val icon = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.sun,
    )

    val bigPictureStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(icon)
        .bigLargeIcon(null as Bitmap?)

    val builder = NotificationCompat.Builder(
        applicationContext,
        "main_channel"
    )
        .setSmallIcon(R.drawable.notification)
        .setContentTitle(messageTitle)
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setStyle(bigPictureStyle)
        .setLargeIcon(icon)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID,builder.build())

}