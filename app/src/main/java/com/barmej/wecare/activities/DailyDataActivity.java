package com.barmej.wecare.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barmej.wecare.R;
import com.barmej.wecare.database.MainViewModel;
import com.barmej.wecare.database.Notifications;

import java.util.ArrayList;
import java.util.List;

public class DailyDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner dataSpinner;
    private TextView notificationsTextView;
    private List<Notifications> items;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);

        notificationsTextView = findViewById(R.id.text_vew_notifications_count);
        dataSpinner = findViewById(R.id.spinner);

        items = new ArrayList<>();
        final ArrayAdapter<Notifications> adapter = new ArrayAdapter<Notifications>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataSpinner.setAdapter(adapter);
        dataSpinner.setOnItemSelectedListener(this);

        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllNotifications().observe(this, new Observer<List<Notifications>>() {
          @Override
          public void onChanged(List<Notifications> notifications) {
             items.clear();
             items.addAll(notifications);
             adapter.notifyDataSetChanged();
          }
      });





    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       Notifications notifications = items.get(position);
       notificationsTextView.setText(String.valueOf(notifications.getNotificationsCount()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }




    }


