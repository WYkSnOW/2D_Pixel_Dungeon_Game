package com.example.myapplication.Model.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.View.main.MainActivity;

public enum ButtonImage implements BitmapMethods {
    MENU_START(
            R.drawable.mainmenu_button_start,
            300, 140,
            true,
            1
    ),


    PLAYING_MENU(
            R.drawable.playing_button_menu,
            140,
            140,
            true, 1
    ),

    UI_BAR1(
            R.drawable.ui_bar_1,
            GameConstants.UiSize.UI_BAR1_WIDTH,
            GameConstants.UiSize.UI_BAR1_HEIGHT,
            false,
            10
    ),

    UI_HOLDER1(
            R.drawable.ui_holder1,
            GameConstants.UiSize.UI_Holder1_WIDTH,
            GameConstants.UiSize.UI_Holder1_HEIGHT,
            false,
            15
    );

    private int width, height,scale;

    private Bitmap normal, pushed;
    ButtonImage(int resID, int width, int height, boolean haveAnim, int scale) {
        options.inScaled = false;
        this.height = height;
        this.width = width;
        this.scale = scale;

        Bitmap buttonAtlas = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        normal = Bitmap.createBitmap(buttonAtlas, 0, 0, width, height);
        if (haveAnim) {
            pushed = Bitmap.createBitmap(buttonAtlas, width, 0, width, height);
        } else {
            pushed = normal;
        }
        normal = Bitmap.createScaledBitmap(normal, (width * scale), (height * scale), false);
        pushed = Bitmap.createScaledBitmap(pushed, (width * scale), (height * scale), false);

    }

    public int getWidth() {
        return width * scale;
    }

    public int getHeight() {
        return height * scale;
    }

    public Bitmap getBtnImg(boolean isBtnPush) {
        if (isBtnPush) {
            return pushed;
        }
        return normal;

    }
}
