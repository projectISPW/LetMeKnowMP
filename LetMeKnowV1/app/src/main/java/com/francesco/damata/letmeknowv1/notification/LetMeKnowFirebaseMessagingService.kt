package com.francesco.damata.letmeknowv1.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.francesco.damata.letmeknowv1.MainActivity
import com.francesco.damata.letmeknowv1.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
///Continua a vedere come possa funzionare la notifica
class LetMeKnowFirebaseMessagingService : FirebaseMessagingService() {
    fun showNotification(
            channelId: String,
            notificationId: Int,
            textTitle: String,
            textContent: String,
            priority: Int = NotificationCompat.PRIORITY_DEFAULT
        ) {
            val intent = Intent(this, MainActivity::class.java).apply {     //Qui c'è un NUllPOinter xception
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent =
                PendingIntent
                    .getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                                or PendingIntent.FLAG_UPDATE_CURRENT
                    )
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(priority)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel =
                    NotificationChannel(channelId, "MessageNotif", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
            }
            notificationManager.notify(notificationId, builder.build())
            with(NotificationManagerCompat.from(this)) {
                //        notify(notificationId, builder.build())
            }
    }
}