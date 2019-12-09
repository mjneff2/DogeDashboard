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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private static String APIURL = "http://shibe.online/api/shibes?count=1&urls=true&httpsUrls=false";
    protected ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image);

        Button newDoge = findViewById(R.id.newDoge);
        newDoge.setOnClickListener(unused -> getNewDoge());

        //SETTINGS BUTTON
        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openSettings();
            }
        });

        queue = Volley.newRequestQueue(this);
        updateImage();
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
        updateImage();
    }

    public void updateImage() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        new RetrieveImageTask().execute(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Main", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    public class RetrieveImageTask extends AsyncTask<String,Void, Bitmap> {
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                Log.d("ImageTask", "Bitmap was null");
            }
        }

        protected Bitmap doInBackground(String... params) {
            String toUrl = params[0];
            return getBitmapFromUrl(toUrl.substring(2, toUrl.length() - 2));
        }

        private Bitmap getBitmapFromUrl(String srcUrl) {
            try {
                URL url = new URL(srcUrl);
                Log.d("ImageTask", "Getting image from URL: " + url.toString());
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input=connection.getInputStream();
                Bitmap myBitmap=BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
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
