package com.example.dogedashboard;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class DogeApp extends Application {
    public static final String CHANNEL_ID = "channelID";


    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Doge App", importance);
            channel.setDescription("The Doge App sends you a notification when your new doge is ready");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    //tried
   /*public class NotificationHelper extends ContextWrapper {
        public static final String channelID = "channelID";
        public static final String channelName = "Channel Name";

        private NotificationManager mManager;

        public NotificationHelper(Context base) {
            super(base);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createChannel();
            }
        }

        @TargetApi(Build.VERSION_CODES.O)
        private void createChannel() {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

            getManager().createNotificationChannel(channel);
        }

        public NotificationManager getManager() {
            if (mManager == null) {
                mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }

            return mManager;
        }

        public NotificationCompat.Builder getChannelNotification() {
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle("Doge App")
                    .setContentText("Your New Doge is Ready!!")
                    .setSmallIcon(R.drawable.ic_time);
        }
   }*/

}
