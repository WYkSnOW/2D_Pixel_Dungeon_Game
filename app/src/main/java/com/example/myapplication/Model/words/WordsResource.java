package com.example.myapplication.Model.words;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum WordsResource implements BitmapMethods {
    WORDS(
            R.drawable.words,
            39);
    private final Bitmap spriteSheet;
    private final Bitmap[][] sprites;
    private final String wordsResource = GameConstants.Words.WORDS_RESOURCE;

    WordsResource(
            int resID,
            int length
    ) {
        OPTIONS.inScaled = false;
        int height = 5;
        int xPos = 0;


        sprites = new Bitmap[1][length];
        spriteSheet = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        for (int i = 0; i < sprites[0].length; i++) {
            int width = (int) getWordWidth(i);
            Bitmap temp = Bitmap.createBitmap(
                    spriteSheet, xPos, 0, xPos + width, height
            );
            sprites[0][i] = temp;
            xPos += width;
        }

    }

    private float getWordWidth(int i) {
        if (i == 18 || i == 37 || i == 38) {
            return 1;
        } else if (i == 19 || i == 21) {
            return 2;
        } else {
            return 3;
        }
    }

    public void drawWords(Canvas c) {
        float xPos = 0;
        float space = 1;
//        for (int i = 0; i < sprites[0].length; i++) {
//            c.drawBitmap(
//                    sprites[0][i],
//                    xPos,
//                    0,
//                    null);
//            xPos += (getWordWidth(i) + space);
//        }
        c.drawBitmap(spriteSheet,
                0,
                0,
                null);
    }




}
