package com.example.myapplication.Model.environments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.ViewModel.MainViewModel;

public enum Floor implements BitmapMethods {
    OUTSIDE(R.drawable.map_resource, 13, 12);



    private Bitmap[] sprites;

    Floor(int resID, int tilesInWidth, int tilesInHeight) {
        OPTIONS.inScaled = false;
        sprites = new Bitmap[tilesInHeight * tilesInWidth];
        Bitmap spriteSheet
                = BitmapFactory.decodeResource(
                        MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        for (int j = 0; j < tilesInHeight; j++) {
            for (int i = 0; i < tilesInWidth; i++) {
                int index = j * tilesInWidth + i;
                sprites[index]
                        = getScaledBitmap(Bitmap.createBitmap(
                                spriteSheet,
                        GameConstants.Sprite.DEFAULT_SIZE * i,
                        GameConstants.Sprite.DEFAULT_SIZE * j,
                        GameConstants.Sprite.DEFAULT_SIZE,
                        GameConstants.Sprite.DEFAULT_SIZE)
                );
            }
        }

    }

    public Bitmap getSprite(int id) {
        return sprites[id];
    }
}
