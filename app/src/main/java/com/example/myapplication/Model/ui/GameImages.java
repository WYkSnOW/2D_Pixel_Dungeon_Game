package com.example.myapplication.Model.ui;

import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_SCALE;
import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_WIDTH;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameImages implements BitmapMethods {


    //MAINMENU_MENUBG(R.drawable.mainmenu_menubackground),
    END_SCREEN_CLOUD_BACKGROUND(
            R.drawable.clouds,
            CLOUD_BACKGROUND_WIDTH,
            CLOUD_BACKGROUND_HEIGHT,
            CLOUD_BACKGROUND_SCALE);
    private final Bitmap image;

    GameImages(int resID, int width, int height, double scale) {
        OPTIONS.inScaled = false;
        Bitmap temp = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        image = Bitmap.createScaledBitmap(temp, (int) (width * scale), (int) (height * scale), false);
    }

    public Bitmap getImage() {
        return image;
    }
}