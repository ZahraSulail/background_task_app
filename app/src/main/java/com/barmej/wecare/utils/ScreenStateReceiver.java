package com.barmej.wecare.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class ScreenStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
         if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){

             //Toast.makeText((context, "screenOn"+ screenOff, Toast.LENGTH_SHORT ).show();
             WorkManager.getInstance(context).cancelAllWork();

         }else{
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {


                //Toast.makeText((context, "screenOFF"+ screenOff, Toast.LENGTH_SHORT ).show();
                PeriodicWorkRequest myWorkRequest =
                        new PeriodicWorkRequest.Builder(AppWorker.class, 5, TimeUnit.MINUTES)
                                .setInitialDelay(5, TimeUnit.MINUTES)
                                // Constraints
                                .build();

                WorkManager.getInstance(context).enqueue(myWorkRequest);

            }
         }



    }
}
