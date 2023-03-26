package com.example.practicalasm;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button leaderboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaderboardButton= findViewById(R.id.leaderboard_button);
        startButton = findViewById(R.id.start_button);

        if (startButton != null) {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Level1.class);
                    startActivity(intent);
                }
            });
        }

        if (leaderboardButton != null) {
            leaderboardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Leaderboard.class);
                    startActivity(intent);
                }
            });
        }
    }
}

