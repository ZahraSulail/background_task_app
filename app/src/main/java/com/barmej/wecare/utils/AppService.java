package com.barmej.wecare.utils;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AppService extends Service {
    public static final String TAG = AppService.class.getSimpleName();

    // ScreenReceiver object
    ScreenStateReceiver screenOnOffReceiver;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public AppService() {

    }
    @Override
    public void onCreate() {
        super.onCreate();
        // Register receiver that handles screen On and screen Off state
        Notification notification = NotificationsUtils.getSyncNotification(this);
        startForeground(NotificationsUtils.BOOT_NOTIFICATION_ID, notification);
        registerScreenOnOffReceiver();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "unRegisterReceiver");
        unregisterReceiver(screenOnOffReceiver);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
/*
  register method to register the OnOfReceiver
 */
    private void registerScreenOnOffReceiver() {
        screenOnOffReceiver =  new ScreenStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(screenOnOffReceiver, filter);
    }
}
