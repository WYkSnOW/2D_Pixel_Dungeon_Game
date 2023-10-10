package com.example.myapplication.Model.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;

import com.example.myapplication.R;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameCharacters implements BitmapMethods {


    WARRIOR(
            R.drawable.warrior_character_list,
            new PointF(GameConstants.CharacterDefault.WARRIOR_WIDTH,
            GameConstants.CharacterDefault.WARRIOR_HEIGHT),
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_X,
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_Y,
            GameConstants.CharacterDefault.WARRIOR_SCALE,
            GameConstants.CharacterDefault.WARRIOR_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.WARRIOR_HITBOX_OFF_SET_Y
    ),

    WITCH(
            R.drawable.witch_character_list,
            new PointF(GameConstants.CharacterDefault.WITCH_WIDTH,
                    GameConstants.CharacterDefault.WITCH_HEIGHT),
            GameConstants.CharacterDefault.WITCH_ANIMATION_X,
            GameConstants.CharacterDefault.WITCH_ANIMATION_Y,
            GameConstants.CharacterDefault.WITCH_SCALE,
            GameConstants.CharacterDefault.WITCH_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.WITCH_HITBOX_OFF_SET_Y
    ),

    TERESA(
            R.drawable.teresa_character_list,
            new PointF(GameConstants.CharacterDefault.TERESA_WIDTH,
                    GameConstants.CharacterDefault.TERESA_HEIGHT),
            GameConstants.CharacterDefault.TERESA_ANIMATION_X,
            GameConstants.CharacterDefault.TERESA_ANIMATION_Y,
            GameConstants.CharacterDefault.TERESA_SCALE,
            GameConstants.CharacterDefault.TERESA_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.TERESA_HITBOX_OFF_SET_Y
    ),

    ZOMBIE(
            R.drawable.zombie_mob_list,
            new PointF(GameConstants.MobAnimDefault.ZOMBIE_WIDTH,
                    GameConstants.MobAnimDefault.ZOMBIE_HEIGHT),
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_X,
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_Y,
            6,
            2,
            4

    );


    private Bitmap spriteSheet;
    private Bitmap[][] sprites; //
    // 素材一共Y行 * X列格，即[Y][X]
    private int characterWidth;
    private int characterHeight;
    private int scale;
    private int maxAnimIndex;
    private int hitBoxOffSetX;
    private int hitBoxOffSetY;



    GameCharacters(
            int resID,
            PointF size,
            int animationX, int animationY,
            int scale,
            int hitBoxOffSetX, int hitBoxOffSetY
    ) {

        int width = (int) size.x;
        int height = (int) size.y;

        this.scale = scale;
        this.characterWidth = width;
        this.characterHeight = height;
        this.maxAnimIndex = animationX;
        this.hitBoxOffSetX = hitBoxOffSetX;
        this.hitBoxOffSetY = hitBoxOffSetY;

        OPTIONS.inScaled = false;

        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp
                        =  Bitmap.createBitmap(
                                spriteSheet, width * i, height * j, width, height
                );
                sprites[j][i]
                        = Bitmap.createScaledBitmap(
                                temp, (width * scale), (height * scale), false
                );
            }
        }

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
