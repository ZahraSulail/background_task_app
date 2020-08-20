package com.barmej.wecare.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotificationsDao {
    @Insert
    public void addNotifications(Notifications notifications);

    @Query("SELECT * FROM notifications")
    public LiveData<List<Notifications>> getAllNotifications();

    @Query("SELECT * FROM notifications WHERE id == :id Limit 1")
    public Notifications getNotification(int id);

    @Query("SELECT * FROM notifications WHERE date == :date Limit 1")
    public LiveData<Notifications> getNotification(String date);

    @Query("SELECT * FROM notifications WHERE date == :date Limit 1")
    public Notifications getNotificationObject(String date);

    @Query("SELECT * FROM notifications Limit 7")
    public LiveData<List<Notifications>> getLastWeekNotifications();


    @Update
    public void updateNotifications(Notifications notifications);

    @Delete
    public void deleteNotifications(Notifications notifications);
}
