package com.example.myapplication.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.StartScreen;

public enum GameCharacters implements BitmapMethods {


    PLAYER(R.drawable.elf_character_list),
    MOB1(R.drawable.elf_character_list);

    private Bitmap spriteSheet;
    private Bitmap[][] sprites = new Bitmap[2][8]; //素材一共32x32格（每格16x16像素）


    GameCharacters(int resID) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(StartScreen.getGameContext().getResources(), resID, options);
        for(int j = 0; j < sprites.length; j++) {
            for(int i = 0; i < sprites[j].length; i++) {
                sprites[j][i] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, GameConstants.Sprite.DEFAULT_SIZE * i, GameConstants.Sprite.DEFAULT_SIZE * j, GameConstants.Sprite.DEFAULT_SIZE, GameConstants.Sprite.DEFAULT_SIZE));
            }
        }

        //System.out.println("Width: " + spriteSheet.getWidth() + " Height: " + spriteSheet.getHeight());
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public Bitmap getSprite(int yPos, int xPos) {
         return sprites[yPos][xPos];
    } //y素材行数，x//列数
}
