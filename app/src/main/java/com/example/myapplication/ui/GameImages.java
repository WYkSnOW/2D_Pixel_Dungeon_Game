package com.example.myapplication.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.main.MainActivity;

public enum GameImages implements BitmapMethods {


    MAINMENU_MENUBG(R.drawable.mainmenu_menubackground);
    private final Bitmap image;

    GameImages(int resID) {
        options.inScaled = false;
        image = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
    }

    public Bitmap getImage() {
        return image;
    }
}