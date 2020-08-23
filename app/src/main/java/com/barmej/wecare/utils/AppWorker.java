package com.barmej.wecare.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.barmej.wecare.database.AppDataBase;
import com.barmej.wecare.database.Notifications;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppWorker extends Worker {
    private static final String TAG = AppWorker.class.getSimpleName();
    /*
      Constructor
     */
    public AppWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "do_Work");

        NotificationsUtils.showScreeenStateNotification(getApplicationContext());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String today = simpleDateFormat.format(new Date());
        AppDataBase appDataBase = AppDataBase.getInstance(getApplicationContext());
        Notifications notifications =  appDataBase.notificationsDao().getNotificationObject(today);

        if(notifications != null){

            notifications.setNotificationsCount(notifications.getNotificationsCount() + 1);
            appDataBase.notificationsDao().updateNotifications(notifications);
        }else{
            notifications = new Notifications(1, today);
            appDataBase.notificationsDao().addNotifications(notifications);
        }

        NotificationsUtils.showScreeenStateNotification(getApplicationContext());
        return Result.success();
    }
}
