package com.example.myapplication.loopVideo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.main.MainActivity;

public enum GameVideos implements BitmapMethods {


    MAIN_BACK_VIDEO(R.drawable.main_back_video, 1920, 1080, 9, 1, MainActivity.getScaleRatio()),
    CONFIG_BACK_VIDEO(R.drawable.config_back_video, 1920, 1080, 12, 1,MainActivity.getScaleRatio());

    private Bitmap spriteSheet;
    private Bitmap[][] sprites;

    private BitmapFactory.Options options = new BitmapFactory.Options();


    GameVideos(int resID, int width, int height, int animationX, int animationY, double scale) {
        options.inScaled = false;
        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for(int j = 0; j < sprites.length; j++) {
            for(int i = 0; i < sprites[j].length; i++) {
                Bitmap temp =  Bitmap.createBitmap(spriteSheet, width * i, height * j, width, height);
                sprites[j][i] = Bitmap.createScaledBitmap(temp, (int) (width * scale), (int) (height * scale), false);

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
