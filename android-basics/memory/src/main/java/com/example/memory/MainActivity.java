package com.example.memory;

import android.animation.*;
import android.os.*;
import android.view.animation.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private GridLayout gameGrid;
    private Button restartButton;
    private TextView statusText;
    private TextView movesText;

    private MemoryCard firstSelectedCard;
    private MemoryCard secondSelectedCard;
    private boolean canClick = true;
    private int movesCount = 0;
    private int pairsFound = 0;
    private final int totalPairs = 8;

    private final int[] cardImages = {
            R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4,
            R.drawable.card_5, R.drawable.card_6, R.drawable.card_7, R.drawable.card_8
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupGame();

        restartButton.setOnClickListener(v -> restartGame());
    }

    private void initializeViews() {
        gameGrid = findViewById(R.id.gameGrid);
        restartButton = findViewById(R.id.restartButton);
        statusText = findViewById(R.id.statusText);
        movesText = findViewById(R.id.movesText);
    }

    private void setupGame() {
        gameGrid.removeAllViews();
        gameGrid.setColumnCount(4);
        gameGrid.setRowCount(4);

        List<Integer> cardValues = new ArrayList<>();
        for (int i = 0; i < totalPairs; i++) {
            cardValues.add(cardImages[i]);
            cardValues.add(cardImages[i]);
        }

        Collections.shuffle(cardValues);

        for (int i = 0; i < cardValues.size(); i++) {
            MemoryCard card = new MemoryCard(this);
            card.setValue(cardValues.get(i));
            card.setRevealed(false);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i % 4, 1f);
            params.rowSpec = GridLayout.spec(i / 4, 1f);
            params.setMargins(4, 4, 4, 4);

            card.setLayoutParams(params);

            card.setOnClickListener(v -> handleCardClick((MemoryCard) v));

            gameGrid.addView(card);
        }

        updateStatus();
    }

    private void handleCardClick(MemoryCard card) {
        if (!canClick || card.isRevealed() || card.isMatched()) {
            return;
        }

        if (firstSelectedCard == null) {
            // Первая карта
            revealCard(card);
            firstSelectedCard = card;
        } else if (secondSelectedCard == null && card != firstSelectedCard) {
            // Вторая карта
            revealCard(card);
            secondSelectedCard = card;
            movesCount++;

            checkForMatch();
        }
    }

    private void revealCard(final MemoryCard card) {
        card.setRevealed(true);

        // Анимация переворота
        ObjectAnimator flip = ObjectAnimator.ofFloat(card, "rotationY", 0f, 90f);
        flip.setDuration(150);
        flip.setInterpolator(new AccelerateInterpolator());
        flip.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                card.updateAppearance();
                ObjectAnimator flipBack = ObjectAnimator.ofFloat(card, "rotationY", 90f, 0f);
                flipBack.setDuration(150);
                flipBack.setInterpolator(new DecelerateInterpolator());
                flipBack.start();
            }
        });
        flip.start();
    }

    private void hideCard(final MemoryCard card) {
        ObjectAnimator flip = ObjectAnimator.ofFloat(card, "rotationY", 0f, 90f);
        flip.setDuration(150);
        flip.setInterpolator(new AccelerateInterpolator());
        flip.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                card.setRevealed(false);
                card.updateAppearance();
                ObjectAnimator flipBack = ObjectAnimator.ofFloat(card, "rotationY", 90f, 0f);
                flipBack.setDuration(150);
                flipBack.setInterpolator(new DecelerateInterpolator());
                flipBack.start();
            }
        });
        flip.start();
    }

    private void checkForMatch() {
        canClick = false;
        updateStatus();

        new Handler().postDelayed(() -> {
            if (firstSelectedCard.getValue() == secondSelectedCard.getValue()) {
                // Найдена пара
                firstSelectedCard.setMatched(true);
                secondSelectedCard.setMatched(true);
                pairsFound++;

                if (pairsFound == totalPairs) {
                    showWinMessage();
                }
            } else {
                // Не совпали - переворачиваем обратно
                hideCard(firstSelectedCard);
                hideCard(secondSelectedCard);
            }

            firstSelectedCard = null;
            secondSelectedCard = null;
            canClick = true;
            updateStatus();
        }, 1000);
    }

    private void updateStatus() {
        movesText.setText("Ходы: " + movesCount);
        statusText.setText("Найдено пар: " + pairsFound + "/" + totalPairs);
    }

    private void showWinMessage() {
        String message = "Поздравляем! Вы нашли все " + totalPairs + " пар!\nХодов: " + movesCount;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void restartGame() {
        firstSelectedCard = null;
        secondSelectedCard = null;
        canClick = true;
        movesCount = 0;
        pairsFound = 0;

        setupGame();

        Toast.makeText(this, "Новая игра началась!", Toast.LENGTH_SHORT).show();
    }
}