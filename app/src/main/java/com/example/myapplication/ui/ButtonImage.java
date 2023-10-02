package com.example.myapplication.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.main.MainActivity;

public enum ButtonImage implements BitmapMethods {
    MENU_START(R.drawable.mainmenu_button_start, 300, 140),
    PLAYING_MENU(R.drawable.playing_button_menu, 140,140);
    private int width, height;

    private Bitmap normal, pushed;
    ButtonImage(int resID, int width, int height) {
        options.inScaled = false;
        this.height = height;
        this.width = width;

        Bitmap buttonAtlas = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        normal = Bitmap.createBitmap(buttonAtlas, 0, 0, width, height);
        pushed = Bitmap.createBitmap(buttonAtlas, width, 0, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Bitmap getBtnImg(boolean isBtnPush) {
        if (isBtnPush) {
            return pushed;
        }
        return normal;

    }
}
