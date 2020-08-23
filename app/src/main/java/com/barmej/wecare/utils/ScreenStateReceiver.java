package com.barmej.wecare.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class ScreenStateReceiver extends BroadcastReceiver {
  /*
   onReceive to scheduel the notification
   */
    @Override
    public void onReceive(Context context, Intent intent) {
         if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
             WorkManager.getInstance(context).cancelAllWork();

         }else{
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                PeriodicWorkRequest myWorkRequest =
                        new PeriodicWorkRequest.Builder(AppWorker.class, 25, TimeUnit.SECONDS)
                                .setInitialDelay(25, TimeUnit.MINUTES)
                                .build();
                WorkManager.getInstance(context).enqueue(myWorkRequest);
            }
         }
    }
}
