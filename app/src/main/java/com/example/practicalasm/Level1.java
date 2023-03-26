package com.example.practicalasm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    private int score;
    private int highlighted_Button;
    private Button button1, button2, button3, button4;
    private TextView messageTextView, scoreTextView;
    private CountDownTimer timer_5s;

    private TextView timerTextView;
    // Initialize the ArrayList
    private ArrayList<Button> buttonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_1);

        // Initialized Views
        messageTextView = findViewById(R.id.message_textView);
        scoreTextView = findViewById(R.id.score_textView);
        timerTextView = findViewById(R.id.timer_textView);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        buttonList = new ArrayList<>();

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);


        // Start game after 3 seconds

        // Check if button clicked is the correct one

        button1.setOnClickListener(this::handleButtonPress);
        button2.setOnClickListener(this::handleButtonPress);
        button3.setOnClickListener(this::handleButtonPress);
        button4.setOnClickListener(this::handleButtonPress);

        start();
        randomHighlightedButton();
    }

    private void showGameOverDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Level1.this);
        // Times Up and Game Over
        builder.setTitle("Game Over !!!");
        builder.setMessage("Congratulations! You Scored " + score + ". Would You Like to Play Again ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Remain level 1
                Intent intent = new Intent(Level1.this,Level1.class);
                intent.putExtra("indicator", score);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            //Skip Level
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Level1.this,Level2.class);
                intent.putExtra("score_level1", score);
                intent.putExtra("Level", 1);
                startActivity(intent);
                finish();
            }
        });
        builder.show();
    }

    // Start the game for duration 5s
    private void start() {
        timer_5s = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (timer_5s != null && timerTextView != null) {
                    timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
                }
            }
            public void onFinish() {
                // Times Up and lead the user in selecting their choices
                showGameOverDialog();
            }
        }.start();
    }

    // End the timer for duration 5s
    private void end(){
        timer_5s.cancel();
    }

    // Reset Timer
    private void reset() {
        timer_5s.cancel();
        start();
    }

    // Highlight a random button for the user to click
    private void randomHighlightedButton() {

        Random random = new Random();
        int highlighted = random.nextInt(4) + 1;

        // Button Original Colors is in Red Colors
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.RED);
        button3.setBackgroundColor(Color.RED);
        button4.setBackgroundColor(Color.RED);

        // Store the previously highlighted button
        int prevHighlighted = highlighted_Button;

        // After press will become Green Colors
        highlighted_Button = buttonList.get(highlighted - 1).getId();
        View highlightedView = findViewById(highlighted_Button);
        if (highlightedView != null) {
            highlightedView.setBackgroundColor(Color.GREEN);
        }


        // If the previously highlighted button is different from the current one, change its color to red

        if (prevHighlighted != highlighted_Button) {
            View prevHighlightedView = findViewById(prevHighlighted);
            if (prevHighlightedView != null) {
                prevHighlightedView.setBackgroundColor(Color.RED);
            }
        }
    }

    // Counting scores when button being press
    private void handleButtonPress(View v) {

        // Check if the pressed button is the highlighted button
        if (messageTextView != null && scoreTextView != null) {
            if (v.getId() == highlighted_Button) {
                score++;
                scoreTextView.setText(String.format("Score: %d", score));
            }
        }
        randomHighlightedButton();
    }

}


