package com.example.practicalasm;

import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.app.AlertDialog;
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

public class Level5 extends AppCompatActivity {

    private int score;
    private int highlighted_Button;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button button10, button11, button12, button13, button14, button15, button16, button17;
    private Button button18, button19, button20, button21, button22, button23, button24, button25;
    private Button button26, button27, button28, button29, button30, button31, button32, button33;
    private Button button34, button35, button36;
    private TextView messageTextView, scoreTextView;
    private CountDownTimer timer_5s;
    private int count = 0;

    private TextView timerTextView;
    // Initialize the ArrayList
    private ArrayList<Button> buttonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_5);

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
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        button23 = findViewById(R.id.button23);
        button24 = findViewById(R.id.button24);
        button25 = findViewById(R.id.button25);
        button26 = findViewById(R.id.button26);
        button27 = findViewById(R.id.button27);
        button28 = findViewById(R.id.button28);
        button29 = findViewById(R.id.button29);
        button30 = findViewById(R.id.button30);
        button31 = findViewById(R.id.button31);
        button32 = findViewById(R.id.button32);
        button33 = findViewById(R.id.button33);
        button34 = findViewById(R.id.button34);
        button35 = findViewById(R.id.button35);
        button36 = findViewById(R.id.button36);



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
        buttonList.add(button17);
        buttonList.add(button18);
        buttonList.add(button19);
        buttonList.add(button20);
        buttonList.add(button21);
        buttonList.add(button22);
        buttonList.add(button23);
        buttonList.add(button24);
        buttonList.add(button25);
        buttonList.add(button26);
        buttonList.add(button27);
        buttonList.add(button28);
        buttonList.add(button29);
        buttonList.add(button30);
        buttonList.add(button31);
        buttonList.add(button32);
        buttonList.add(button33);
        buttonList.add(button34);
        buttonList.add(button35);
        buttonList.add(button36);



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
        button17.setOnClickListener(this::handleButtonPress);
        button18.setOnClickListener(this::handleButtonPress);
        button19.setOnClickListener(this::handleButtonPress);
        button20.setOnClickListener(this::handleButtonPress);
        button21.setOnClickListener(this::handleButtonPress);
        button22.setOnClickListener(this::handleButtonPress);
        button23.setOnClickListener(this::handleButtonPress);
        button24.setOnClickListener(this::handleButtonPress);
        button25.setOnClickListener(this::handleButtonPress);
        button26.setOnClickListener(this::handleButtonPress);
        button27.setOnClickListener(this::handleButtonPress);
        button28.setOnClickListener(this::handleButtonPress);
        button29.setOnClickListener(this::handleButtonPress);
        button30.setOnClickListener(this::handleButtonPress);
        button31.setOnClickListener(this::handleButtonPress);
        button32.setOnClickListener(this::handleButtonPress);
        button33.setOnClickListener(this::handleButtonPress);
        button34.setOnClickListener(this::handleButtonPress);
        button35.setOnClickListener(this::handleButtonPress);
        button36.setOnClickListener(this::handleButtonPress);

        start();
        randomHighlightedButton();
    }

    private void showGameOverDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);
        // Times Up and Game Over
        builder.setTitle("Game Over !!!");
        builder.setMessage("Congratulations! You Scored " + score + ". Would You Like to Play Again ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Remain Level 5
                Intent intent = new Intent(Level5.this,Level5.class);
                intent.putExtra("indicator", score);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Done ", new DialogInterface.OnClickListener() {
            @Override
            //Leaderboard Input
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Level5.this,Input.class);
                intent.putExtra("score_level4", score);
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
        int highlighted = random.nextInt(36) + 1;

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
        button17.setBackgroundColor(Color.RED);
        button18.setBackgroundColor(Color.RED);
        button19.setBackgroundColor(Color.RED);
        button20.setBackgroundColor(Color.RED);
        button21.setBackgroundColor(Color.RED);
        button22.setBackgroundColor(Color.RED);
        button23.setBackgroundColor(Color.RED);
        button24.setBackgroundColor(Color.RED);
        button25.setBackgroundColor(Color.RED);
        button26.setBackgroundColor(Color.RED);
        button27.setBackgroundColor(Color.RED);
        button28.setBackgroundColor(Color.RED);
        button29.setBackgroundColor(Color.RED);
        button30.setBackgroundColor(Color.RED);
        button31.setBackgroundColor(Color.RED);
        button32.setBackgroundColor(Color.RED);
        button33.setBackgroundColor(Color.RED);
        button34.setBackgroundColor(Color.RED);
        button35.setBackgroundColor(Color.RED);
        button36.setBackgroundColor(Color.RED);

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

