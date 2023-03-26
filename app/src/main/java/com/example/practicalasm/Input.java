package com.example.practicalasm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Input extends AppCompatActivity {

    private int level;
    private int score;
    private EditText usernameEditText;
    private SharedPreferences sharedPreferences;
    private ArrayList<Score> scoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        Intent intent = getIntent();
        level = intent.getIntExtra("Level", 0);
        score = intent.getIntExtra("score_level5", 0);


        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
        usernameEditText = findViewById(R.id.usernameEditText);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get username and pass it back to MainActivity
                String username = usernameEditText.getText().toString();
                saveData(username, score);
                Intent intent = new Intent(Input.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Retrieve scores data from shared preferences
        sharedPreferences = getSharedPreferences("ScoresData", Context.MODE_PRIVATE);
        String scoresJsonString = sharedPreferences.getString("Scores", null);
        scoresList = new ArrayList<>();
        if (scoresJsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(scoresJsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    String username = jsonArray.getJSONObject(i).getString("username");
                    int score = jsonArray.getJSONObject(i).getInt("score");
                    Score newScore = new Score(username, score);
                    scoresList.add(newScore);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveData(String username, int score) {
        Score newScore = new Score(username, score);
        scoresList.add(newScore);

        saveScoresData();

    }

    private void saveScoresData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < scoresList.size(); i++) {
            Score score = scoresList.get(i);
            try {
                jsonArray.put(i, score.toJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        editor.putString("Scores", jsonArray.toString());
        editor.apply();
    }

    private static class Score {
        String username;
        int score;

        public Score(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public JSONArray toJSON() {
            JSONArray jsonArray = new JSONArray();
            try {
                jsonArray.put(0, this.username);
                jsonArray.put(1, this.score);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }
    }

}

