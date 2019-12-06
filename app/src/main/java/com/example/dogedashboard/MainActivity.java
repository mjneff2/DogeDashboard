package com.example.dogedashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newDoge = findViewById(R.id.newDoge);
        newDoge.setOnClickListener(unused -> getNewDoge());

        Button settings = findViewById(R.id.settings);
        settings.setOnClickListener(unused -> openSettings());
    }

    /**
     * Refreshes image with a new doge from web api.
     */
    private void getNewDoge() {
        Log.d("Main", "Get new doge was pressed");
    }

    /**
     * Opens settings dialogue.
     */
    private void openSettings() {
        Log.d("Main", "Settings button was pressed");
    }
}
