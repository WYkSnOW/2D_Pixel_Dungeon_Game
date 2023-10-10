package com.example.myapplication.Model.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameImages implements BitmapMethods {


    MAINMENU_MENUBG(R.drawable.mainmenu_menubackground);
    private final Bitmap image;

    GameImages(int resID) {
        OPTIONS.inScaled = false;
        image = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
    }

    public Bitmap getImage() {
        return image;
    }
}