package com.barmej.wecare.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel  extends AndroidViewModel {
    /*
    AppDatabase variable
     */
    private AppDataBase mDatabase;

    public MainViewModel(@NonNull Application application) {
        super(application);
        /*
        create AppDatabace object
         */
        mDatabase = AppDataBase.getInstance(application.getApplicationContext());

    }

    public LiveData<List<Notifications>> getAllNotifications(){
        return  mDatabase.notificationsDao().getAllNotifications();
    }

    public  LiveData<Notifications> getNotifications(String date) {
        return mDatabase.notificationsDao().getNotification(date);
    }

    public LiveData<List<Notifications>> getLastWeekNotifications(){
        return  mDatabase.notificationsDao().getLastWeekNotifications();
    }


}
