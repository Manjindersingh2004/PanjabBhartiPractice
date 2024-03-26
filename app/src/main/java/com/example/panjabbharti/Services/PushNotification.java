package com.example.panjabbharti.Services;

import android.app.Notification;
import android.app.NotificationManager;

import androidx.annotation.NonNull;

import com.example.panjabbharti.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


//Send any type of notification
//Need to be send message manually by typing in firebasecloud messaging
public class PushNotification extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        if (message.getNotification()!=null){
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            Notification notification = new Notification.Builder(getApplicationContext(), message.getNotification().getChannelId()).setContentText(message.getNotification().getTitle()).setSubText(message.getNotification().getBody()).setChannelId(message.getNotification().getChannelId()).setAutoCancel(true).build();

            notificationManager.notify(1,notification);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
