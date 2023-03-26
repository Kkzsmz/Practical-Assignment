package com.example.practicalasm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


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

public class Level3 extends AppCompatActivity {
    private int score;
    private int highlighted_Button;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button button10, button11, button12, button13, button14, button15, button16;
    private TextView messageTextView, scoreTextView;
    private CountDownTimer timer_5s;
    private int count = 0;

    private TextView timerTextView;
    // Initialize the ArrayList
    private ArrayList<Button> buttonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_3);

        // Initialized Views
        messageTextView = findViewById(R.id.message_textView);
        scoreTextView = findViewById(R.id.score_textView);
        timerTextView = findViewById(R.id.timer_textView);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);





        buttonList = new ArrayList<>();

        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);
        buttonList.add(button8);
        buttonList.add(button9);
        buttonList.add(button10);
        buttonList.add(button11);
        buttonList.add(button12);
        buttonList.add(button13);
        buttonList.add(button14);
        buttonList.add(button15);
        buttonList.add(button16);


        // Start game after 3 seconds

        // Check if button clicked is the correct one

        button1.setOnClickListener(this::handleButtonPress);
        button2.setOnClickListener(this::handleButtonPress);
        button3.setOnClickListener(this::handleButtonPress);
        button4.setOnClickListener(this::handleButtonPress);
        button5.setOnClickListener(this::handleButtonPress);
        button6.setOnClickListener(this::handleButtonPress);
        button7.setOnClickListener(this::handleButtonPress);
        button8.setOnClickListener(this::handleButtonPress);
        button9.setOnClickListener(this::handleButtonPress);
        button10.setOnClickListener(this::handleButtonPress);
        button11.setOnClickListener(this::handleButtonPress);
        button12.setOnClickListener(this::handleButtonPress);
        button13.setOnClickListener(this::handleButtonPress);
        button14.setOnClickListener(this::handleButtonPress);
        button15.setOnClickListener(this::handleButtonPress);
        button16.setOnClickListener(this::handleButtonPress);

        start();
        randomHighlightedButton();
    }

    private void showGameOverDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Level3.this);
        // Times Up and Game Over
        builder.setTitle("Game Over !!!");
        builder.setMessage("Congratulations! You Scored " + score + ". Would You Like to Play Again ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Remain Level 3
                Intent intent = new Intent(Level3.this,Level3.class);
                intent.putExtra("indicator", score);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Skip", new DialogInterface.OnClickListener() {
            @Override
            //Skip to Level 4
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Level3.this,Level4.class);
                intent.putExtra("score_level3", score);
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
        int highlighted = random.nextInt(16) + 1;

        // Button Original Colors is in Red Colors
        button1.setBackgroundColor(Color.RED);
        button2.setBackgroundColor(Color.RED);
        button3.setBackgroundColor(Color.RED);
        button4.setBackgroundColor(Color.RED);
        button5.setBackgroundColor(Color.RED);
        button6.setBackgroundColor(Color.RED);
        button7.setBackgroundColor(Color.RED);
        button8.setBackgroundColor(Color.RED);
        button9.setBackgroundColor(Color.RED);
        button10.setBackgroundColor(Color.RED);
        button11.setBackgroundColor(Color.RED);
        button12.setBackgroundColor(Color.RED);
        button13.setBackgroundColor(Color.RED);
        button14.setBackgroundColor(Color.RED);
        button15.setBackgroundColor(Color.RED);
        button16.setBackgroundColor(Color.RED);

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

