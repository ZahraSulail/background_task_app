package com.barmej.wecare.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.barmej.wecare.R;
import com.barmej.wecare.activities.MainActivity;
import com.barmej.wecare.database.Notifications;

public class NotificationsUtils {
    /*
      Notifications and Notifications channel id;s to call them inside methods
     */

    private static final String SCREEN_STATE_CHANNEL_ID = "screenStateChannelId";
    private static final int APP_NOTIFICATIONS_ID = 1;

    public static final int BOOT_NOTIFICATION_ID = 2;

    public static void createScreenStateChannel(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = context.getString(R.string.screen_state_channel_name);
            String description = context.getString(R.string.screen_state_channel_description);

            NotificationChannel channel = new NotificationChannel(
                    SCREEN_STATE_CHANNEL_ID,
                    name,
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void showScreeenStateNotification(Context context, Notifications notifications){

        if(notifications != null){
            String notificationTitte = context.getString(R.string.app_name);
            String notificationText = String.format(context.getString(R.string.format_notification));

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,SCREEN_STATE_CHANNEL_ID);
                    builder.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
                    builder.setSmallIcon(R.drawable.ic_message);
                    builder.setContentTitle(notificationTitte);
                    builder.setContentText(notificationText);
                    builder.setAutoCancel(true);


                    Intent intent = new Intent(context, MainActivity.class);
                    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
                    taskStackBuilder.addNextIntentWithParentStack(intent);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                    builder.setContentIntent(pendingIntent);
                    Notification notification = builder.build();

            NotificationManager notificationManagr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManagr.notify(APP_NOTIFICATIONS_ID, notification);
        }
    }



    public static Notification getSyncNotification(Context context){
      String notificationTitle = context.getString(R.string.app_name);
      String notificationText = context.getString(R.string.boot_notification);
      NotificationCompat.Builder builder = new NotificationCompat.Builder(context, SCREEN_STATE_CHANNEL_ID)
              .setOngoing(true)
              .setContentTitle(notificationTitle)
              .setContentText(notificationText)
              .setSmallIcon(R.drawable.ic_message);
      return builder.build();
    }


}
