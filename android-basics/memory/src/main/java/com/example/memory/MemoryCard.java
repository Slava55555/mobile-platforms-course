package com.example.memory;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class MemoryCard extends AppCompatImageView {

    private int value;
    private boolean isRevealed;
    private boolean isMatched;

    public MemoryCard(Context context) {
        super(context);
        init();
    }

    public MemoryCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MemoryCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundColor(Color.BLUE);
        setScaleType(ScaleType.CENTER_CROP);
        setPadding(8, 8, 8, 8);
        updateAppearance();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateAppearance();
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
        updateAppearance();
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        updateAppearance();
    }

    public void updateAppearance() {
        if (isMatched) {
            // Найденная пара - полупрозрачная
            setAlpha(0.3f);
            setEnabled(false);
        } else if (isRevealed) {
            // Открытая карта - показываем изображение
            setImageResource(value);
            setBackgroundColor(Color.WHITE);
            setAlpha(1.0f);
        } else {
            // Закрытая карта - рубашка
            setImageResource(0);
            setBackgroundColor(Color.BLUE); // Синий цвет
            setAlpha(1.0f);
        }
    }
}