package com.barmej.wecare.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.barmej.wecare.R;
import com.barmej.wecare.database.MainViewModel;
import com.barmej.wecare.database.Notifications;
import com.barmej.wecare.utils.AppService;
import com.barmej.wecare.utils.NotificationsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
     TextView to display daily notifications
     */
    private TextView todayNotificationsTextView;
    /*
    TextView to display last notificatons per week
    */
    private TextView weeklyAvarageTextView;
    /*
     Open DailyData activity button
    */
    private Button dailyDataButton;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call createScreenStateChannel() method
        NotificationsUtils.createScreenStateChannel(this);

        /*
         Find views by Ids
         */
        todayNotificationsTextView = findViewById(R.id.text_vew_today_notifications);
        weeklyAvarageTextView = findViewById(R.id.text_vew_weekly_avarage);

        /*
         Get today date
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String today = simpleDateFormat.format(new Date());

        /*
         Obesrver to get today's notification and set it in textView
         */
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getNotifications(today).observe(this, new Observer<Notifications>() {
            @Override
            public void onChanged(Notifications notifications) {
                if(notifications != null){

               todayNotificationsTextView.setText(String.valueOf(notifications.getNotificationsCount()));
            }else{
                    todayNotificationsTextView.setText(String.valueOf(0));
                }
            }

        });

        /*
         Observer to get weekly avarage of notifications count and set the result in a textView
         */
        mainViewModel.getLastWeekNotifications().observe(this, new Observer<List<Notifications>>() {
            @Override
            public void onChanged(List<Notifications> notifications) {
                // An Avarage exeprission to get weekly agarage of notifications
                int total = 0;
                if(notifications.size() != 0){
                    for(int i = 0; i < notifications.size(); i++){
                        total = total + notifications.get(i).getNotificationsCount();
                    }
                    int avrage = total / notifications.size();
                    weeklyAvarageTextView.setText(String.valueOf(avrage));
                }else{
                    weeklyAvarageTextView.setText(String.valueOf(0));

                }
                }
        });

        /*
          Create Listener to call startDailyActivity() method
         */
        findViewById(R.id.button_daily_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDailyDataActivity();
            }
        });

        /*
          Start the foregroundService
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                startForegroundService(new Intent(this, AppService.class));

            }else{
                startService(new Intent(this, AppService.class));
            }
    }

    /*
     An Intent to start an activity
     */
    private void startDailyDataActivity(){
        Intent intent = new Intent(this, DailyDataActivity.class );
        startActivity(intent);
    }
}
