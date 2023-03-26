package com.example.practicalasm;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard extends AppCompatActivity {

    private ListView leaderboardListView;
    private ArrayList<Score> scoresList;
    private File scoresFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        // Initialize UI
        leaderboardListView = findViewById(R.id.leaderboardListView);

        // Retrieve scores data from file
        scoresList = new ArrayList<>();
        scoresFile = new File(getFilesDir(), "scores.json");
        if (scoresFile.exists()) {
            try {
                FileInputStream fileInputStream = openFileInput("scores.json");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String username = jsonObject.getString("username");
                    int score = jsonObject.getInt("score");
                    Score newScore = new Score(username, score);
                    scoresList.add(newScore);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }

        // Sort scores in descending order by score
        Collections.sort(scoresList, new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return s2.score - s1.score;
            }
        });

        // Remove any scores beyond the first 25
        if (scoresList.size() > 25) {
            scoresList.subList(25, scoresList.size()).clear();
        }


        // Set up leaderboard ListView
        LeaderboardAdapter adapter = new LeaderboardAdapter(this, scoresList);
        leaderboardListView.setAdapter(adapter);
    }

    private static class Score {
        String username;
        int score;

        public Score(String username, int score) {
            this.username = username;
            this.score = score;
        }

        public JSONObject toJSON() throws JSONException {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("score", score);
            return jsonObject;
        }
    }

    private void saveScoresData() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Score score : scoresList) {
                jsonArray.put(score.toJSON());
            }
            FileOutputStream fileOutputStream = openFileOutput("scores.json", Context.MODE_PRIVATE);
            fileOutputStream.write(jsonArray.toString().getBytes());
            fileOutputStream.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static class LeaderboardAdapter extends ArrayAdapter<Score> {

        private Context context;
        private ArrayList<Score> scoresList;

        public LeaderboardAdapter(Context context, ArrayList<Score> scoresList) {
            super(context, R.layout.leaderboard, scoresList);
            this.context = context;
            this.scoresList = scoresList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.leaderboard, parent, false);

            TextView usernameTextView = rowView.findViewById(R.id.usernameTextView);
            TextView scoreTextView = rowView.findViewById(R.id.scoreTextView);

            Score score = scoresList.get(position);

            usernameTextView.setText(score.username);
            scoreTextView.setText(String.valueOf(score.score));

            return rowView;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveScoresData();
    }

}



