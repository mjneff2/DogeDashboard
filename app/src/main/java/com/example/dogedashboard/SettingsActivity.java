package com.example.dogedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //SET UP CREATE NOTIFICATIONS BUTTON
        Button createNotification = findViewById((R.id.createNotification));
        createNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = getIntent();
                int hourD = i.getIntExtra("hourD",12);
                int minuteD = i.getIntExtra("minuteD", 12);
                Reminder r = new Reminder(view, hourD, minuteD);
            }
        });

        //TIME PICKER BUTTON
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePick = new TimePicker();
                timePick.show(getSupportFragmentManager(), "time picker");
            }
        });


    }

    public void sendOnChannel1(View view) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        TextView editTextTitle = findViewById(R.id.edit_text_title);
        TextView editTextMessage = findViewById(R.id.edit_text_message);
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, DogeApp.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_time)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

                .build();
        notificationManagerCompat.notify(1,notification);

    }
    public class Reminder {
        Timer timer;
        public Reminder(View v, int hourD, int minuteD) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourD);
            c.set(Calendar.MINUTE, minuteD);
            c.set(Calendar.SECOND, 0);
            Date t = c.getTime();
            System.out.println(t);
            timer = new Timer();
            timer.schedule(new RemindTask(v), t);
        }

        class RemindTask extends TimerTask {
            private View vW;
            public RemindTask(View v) {
                vW = v;

            }
            public void run() {
                sendOnChannel1(vW);
                timer.cancel(); //Terminate the timer thread
            }
        }
    }



}
