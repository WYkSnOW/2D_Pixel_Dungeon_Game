package com.example.myapplication.Model.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.View.main.MainActivity;

public enum GameCharacters implements BitmapMethods {


    WARRIOR(
            R.drawable.warrior_character_list,
            55,
            42,
            8,
            6,
            4,
            24,
            13
    ),

    WITCH(
            R.drawable.witch_character_list,
            43,
            47,
            6,
            6,
            3,
            11,
            14
    ),

    TERESA(
            R.drawable.teresa_character_list,
            43,
            40,
            8,
            6,
            4,
            19,
            13
    ),

    ZOMBIE(
            R.drawable.zombie_mob_list,
            GameConstants.MobAnimDefault.ZOMBIE_WIDTH,
            GameConstants.MobAnimDefault.ZOMBIE_HEIGHT,
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_X,
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_Y,
            6,
            2,
            4

    );


    private Bitmap spriteSheet;
    private Bitmap[][] sprites; //
    // 素材一共Y行 * X列格，即[Y][X]
    private int characterWidth, characterHeight, scale, maxAnimIndex, hitBoxOffSetX, hitBoxOffSetY;



    GameCharacters(int resID, int width, int height, int animationX, int animationY, int scale, int hitBoxOffSetX, int hitBoxOffSetY) {
        this.scale = scale;
        this.characterWidth = width;
        this.characterHeight = height;
        this.maxAnimIndex = animationX;
        this.hitBoxOffSetX = hitBoxOffSetX;
        this.hitBoxOffSetY = hitBoxOffSetY;

        options.inScaled = false;

        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp =  Bitmap.createBitmap(spriteSheet, width * i, height * j, width, height);
                sprites[j][i] = Bitmap.createScaledBitmap(temp, (width * scale), (height * scale), false);
            }
        }

        //System.out.println("Width: " + spriteSheet.getWidth() + " Height: " + spriteSheet.getHeight());
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }


    public int getHitBoxOffSetX() {
        return hitBoxOffSetX * scale;
    }

    public int getHitBoxOffSetY() {
        return hitBoxOffSetY * scale;
    }


    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos];
    } //y素材行数，x//列数

    public int getCharacterWidth() {
        return characterWidth * scale;
    }

    public int getCharacterHeight() {
        return characterHeight * scale;
    }

    public int getScale() {
        return scale;
    }


    public int getMaxAnimIndex() {
        return maxAnimIndex;
    }
}
