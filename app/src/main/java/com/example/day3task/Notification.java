package com.example.day3task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void notify(View view) {
        NotificationManager notificationManager = (NotificationManager) Notification.this.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        Intent snoozeIntent = new Intent(this, MainActivity0.class);
        snoozeIntent.setAction("ACTION_SNOOZE");
        snoozeIntent.putExtra("EXTRA_NOTIFICATION_ID", 0);
        PendingIntent snoozePendingIntent = PendingIntent.getActivity(Notification.this, 0, snoozeIntent, 0);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.nature));


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Notification.this, channelId)
                .setLargeIcon(BitmapFactory.decodeResource(Notification.this.getResources(), R.drawable.sunlight)).setStyle(style)
                .setSmallIcon(R.drawable.sunlight).setContentTitle("Just Notification Check 1").setTicker("NOTIFICATION 1").setAutoCancel(true)
                .setContentText("Now Go Back 1").addAction(android.R.drawable.btn_star,"Next",snoozePendingIntent);

        Intent intent = new Intent(Notification.this, Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent, 0);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    public void notify2(View view) {
        NotificationManager notificationManager = (NotificationManager) Notification.this.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 2;
        String channelId = "channel-02";
        String channelName = "Channel Name 2";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel2 = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel2);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(Notification.this, channelId)
                .setLargeIcon(BitmapFactory.decodeResource(Notification.this.getResources(), R.drawable.avatar3))
                .setSmallIcon(R.drawable.avatar3).setContentTitle("Just Notification Check 2")
                .setContentText("Now Go Back 2");

        Intent intent = new Intent(Notification.this, Notification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(Notification.this, 0, intent, 0);
        mBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }
//    private RemoteViews remoteView(String message)
//    {
//        RemoteViews views;
//        views = new RemoteViews(getPackageName(), R.layout.activity_notification);
//        //views.setImageViewBitmap(R.id., bitmap);
//        views.setImageViewBitmap(R.id.videoView, BitmapFactory.decodeResource(getResources(), R.drawable.nature));
//        //views.setTextViewText(R.id.YOUR_BIG_TEXTVIEW_ID_FROM_LAYOUT, message);
//        return views;
//    }
}