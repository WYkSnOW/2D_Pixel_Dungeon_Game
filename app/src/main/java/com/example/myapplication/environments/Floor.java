package com.example.myapplication.environments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.StartScreen;

public enum Floor implements BitmapMethods {
    OUTSIDE(R.drawable.map1, 30, 20);



    private Bitmap[] sprites;

    Floor(int resID, int tilesInWidth, int tilesInHeight){
        options.inScaled = false;
        sprites = new Bitmap[tilesInHeight * tilesInWidth];
        Bitmap spriteSheet = BitmapFactory.decodeResource(StartScreen.getGameContext().getResources(), resID, options);
        for(int j = 0; j < tilesInHeight; j++) {
            for(int i = 0; i < tilesInWidth; i++) {
                int index = j * tilesInWidth + i;
                sprites[index] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, GameConstants.Sprite.DEFAULT_SIZE * i, GameConstants.Sprite.DEFAULT_SIZE * j, GameConstants.Sprite.DEFAULT_SIZE, GameConstants.Sprite.DEFAULT_SIZE));
            }
        }

    }

    public Bitmap getSprite(int id) {
        return sprites[id];
    }
}
