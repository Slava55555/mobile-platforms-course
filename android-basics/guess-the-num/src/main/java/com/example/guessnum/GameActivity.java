package com.example.guessnum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button buttonLessOrEqual;
    private Button buttonGreater;

    private int min;
    private int max;
    private int currentGuess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonLessOrEqual = findViewById(R.id.buttonLessOrEqual);
        buttonGreater = findViewById(R.id.buttonGreater);

        Intent intent = getIntent();
        min = intent.getIntExtra("MIN_VALUE", 1);
        max = intent.getIntExtra("MAX_VALUE", 100);

        makeGuess();

        buttonLessOrEqual.setOnClickListener(v -> handleAnswer(true));

        buttonGreater.setOnClickListener(v -> handleAnswer(false));
    }

    private void makeGuess() {
        currentGuess = min + (max - min) / 2;

        if (max - min == 1) currentGuess = min;
        else textViewQuestion.setText("Ваше число ≤ " + currentGuess + "?");
    }

    private void handleAnswer(boolean isLessOrEqual) {
        if (isLessOrEqual) {
            max = currentGuess;
        } else {
            min = currentGuess + 1;
        }

        if (min == max) {
            textViewQuestion.setText("Ваше число: " + min + "!");
            disableButtons();
        } else if (max - min == 1) {
            currentGuess = min;
            textViewQuestion.setText("Ваше число ≤ " + currentGuess + "?");
        } else {
            makeGuess();
        }
    }
    private void disableButtons() {
        buttonLessOrEqual.setEnabled(false);
        buttonGreater.setEnabled(false);
    }
}
