package com.barmej.wecare.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notifications.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    /*
    Instance of this class for singlton
     */
    private static final Object LOCK = new Object();

    /**
     * Database file name
     */
    private static final String DATABASE_NAME = "we_care_db";

    /**
     * Instance of this class for Singleton
     */
    private static AppDataBase sInstance;

    /**
     * Method used to get an instance of AppDatabase class
     *
     * @param context Context to use for Room initializations
     * @return an instance of AppDatabase class
     */
    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) sInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDataBase.class,
                        AppDataBase.DATABASE_NAME
                ).build();
            }
        }
        return sInstance;
    }

/**
 * Return object of NotificationsDao to read, write, delete and update notificationsCount
 */

    public abstract NotificationsDao notificationsDao();
}




