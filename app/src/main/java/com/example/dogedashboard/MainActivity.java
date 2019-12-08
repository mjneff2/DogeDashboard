package com.example.dogedashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.dogedashboard.DogeApp.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newDoge = findViewById(R.id.newDoge);
        newDoge.setOnClickListener(unused -> getNewDoge());

        //SETTINGS BUTTON
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openSettings();
            }
        });
    }
    public void openSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    /**
     * Refreshes image with a new doge from web api.
     */
    private void getNewDoge() {
        Log.d("Main", "Get new doge was pressed");
    }


    //setting up notification
    //public void sendOnChannel1(View view) {
        //String title = editTextTitle.getText().toString();
        //String message = editTextMessage.getText().toString();

        //Notification notification = new NotificationCompat.Builder(this, DogeApp.CHANNEL_ID)
                //.setSmallIcon(R.drawable.ic_time)
                //.setContentTitle(title)
                //.setContentText(message)
                //.setPriority(NotificationCompat.PRIORITY_HIGH)
                //.build();
        //notificationManagerCompat.notify(1,notification);

    //}
}
