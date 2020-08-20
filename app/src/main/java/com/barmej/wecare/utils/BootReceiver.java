package com.barmej.wecare.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootReceiver extends BroadcastReceiver {
    /*
       Start service
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
             context.startForegroundService(new Intent(context, AppService.class));

        }else{
                context.startService(new Intent(context, AppService.class));
            }
        }
    }
}


