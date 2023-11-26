package com.example.myapplication.Model.entities.Items;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.myapplication.Model.entities.Items.itemStartegy.ItemStrategy;
import com.example.myapplication.Model.entities.Items.itemStartegy.LoopItemStrategy;
import com.example.myapplication.Model.entities.Items.itemStartegy.ReactiveItem;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum Items implements BitmapMethods {

    CHEST_ONE(
            R.drawable.gold_chest,
            16,
            16,
            2,
            false,
            GameConstants.Sprite.SCALE_MULTIPLIER,
            true),
    READ_HEART(
            R.drawable.red_heart_amin_list,
            32,
            32,
            10,
            true,
            3,
            true),

    BLUE_HEART(
            R.drawable.blue_heart_amin_list,
            32,
            32,
            10,
            true,
            3,
            true),

    YELLOW_HEART(
            R.drawable.yellow_heart_amin_list,
            32,
            32,
            10,
            true,
            3,
            true);


    private int maxAnimIndex;
    private float width;
    private float height;
    private final Bitmap spriteSheet;
    private final Bitmap[][] sprites;
    private ItemStrategy itemStrategy;
    private boolean ableReact;


    Items(int resID, int width, int height,
          int animationX, boolean isLoop, int scale, boolean ableReact) {
        this.width = width * scale;
        this.height = height * scale;
        this.maxAnimIndex = animationX;
        this.ableReact = ableReact;

        if (isLoop) {
            this.itemStrategy = new LoopItemStrategy();
        } else {
            this.itemStrategy = new ReactiveItem();
        }


        OPTIONS.inScaled = false;
        sprites = new Bitmap[1][animationX];
        spriteSheet = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );

        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp = Bitmap.createBitmap(
                        spriteSheet, width * i, 0, width, height
                );
                sprites[j][i] = Bitmap.createScaledBitmap(
                        temp, (int) (width * scale), (int) (height * scale), false
                );

            }
        }
    }

    public boolean isAbleReact() {
        return ableReact;
    }

    public ItemStrategy getItemStrategy() {
        return itemStrategy;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getMaxAnimIndex() {
        return maxAnimIndex;
    }
    public Bitmap getItemImg(int xPos) {
        return sprites[0][xPos]; //y is row in asset，x is list in asset
    }

}