package com.example.guessnum;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextMin;
    private EditText editTextMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        Button buttonStart = findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(v -> startGame());
    }

    private void startGame() {
        String minStr = editTextMin.getText().toString();
        String maxStr = editTextMax.getText().toString();

        try {
            int min = minStr.isEmpty() ? 1 : Integer.parseInt(minStr);
            int max = maxStr.isEmpty() ? 100 : Integer.parseInt(maxStr);

            if (min >= max) {
                Toast.makeText(this, "Максимум должен быть больше минимума", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("MIN_VALUE", min);
            intent.putExtra("MAX_VALUE", max);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_SHORT).show();
        }
    }
}