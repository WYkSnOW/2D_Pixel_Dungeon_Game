package com.example.myapplication.Model.loopVideo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.View.main.MainActivity;

public enum GameVideos implements BitmapMethods {


    MAIN_BACK_VIDEO(
            R.drawable.main_back_video,
            1920,
            1080,
            9,
            1,
            MainActivity.getScaleRatio(),
            8
    ),
    DIFFICULTY_BOX(
            R.drawable.difficulty_box,
            96,
            96,
            4,
            1,
            3,
            4

    ),
    WITCH(
            R.drawable.witch_character_list,
            43,
            47,
            6,
            6,
            6,
            6
    ),
    WITCH_ATTACK_EFFECT(
            R.drawable.witch_attack_effect,
            86,
            47,
            6,
            1,
            3,
            10

    ),
    TERESA(
            R.drawable.teresa_character_list,
            43,
            40,
            8,
            6,
            7,
            6
    ),

    WARRIOR(
            R.drawable.warrior_character_list,
            55,
            42,
            8,
            6,
            7,
            6
    ),

    CONFIG_BACK_VIDEO(
            R.drawable.config_back_video,
            1920,
            1080,
            12,
            1,
            MainActivity.getScaleRatio(),
            12
    );

    private final Bitmap spriteSheet;
    private final Bitmap[][] sprites;
    private int maxAnimIndex;
    private BitmapFactory.Options options = new BitmapFactory.Options();
    private int width, height, cScale, animRate;

    //contain all video resource that is being use
    GameVideos(int resID, int width, int height, int animationX, int animationY, double scale, int animRate) {
        options.inScaled = false;
        this.width = width;
        this.height = height;
        this.cScale = (int) scale;
        this.maxAnimIndex = animationX;
        this.animRate = animRate;


        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp = Bitmap.createBitmap(spriteSheet, width * i, height * j, width, height);
                sprites[j][i] = Bitmap.createScaledBitmap(temp, (int) (width * scale), (int) (height * scale), false);

            }
        }

    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public int getMaxAnimIndex() {
        return maxAnimIndex;
    }
    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos]; //y is row in asset，x is list in asset
    }

    public int getWidth() {
        return width * cScale;
    }

    public int getHeight() {
        return height * cScale;
    }

    public int getAnimRate() {
        return animRate;
    }
}
