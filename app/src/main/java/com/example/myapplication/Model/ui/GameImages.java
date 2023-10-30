package com.example.myapplication.Model.ui;

import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_SCALE;
import static com.example.myapplication.Model.helper.GameConstants.ImageDefault.CLOUD_BACKGROUND_WIDTH;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameImages implements BitmapMethods {




    //MAINMENU_MENUBG(R.drawable.mainmenu_menubackground),
    END_SCREEN_CLOUD_BACKGROUND(
            R.drawable.clouds,
            CLOUD_BACKGROUND_WIDTH,
            CLOUD_BACKGROUND_HEIGHT,
            CLOUD_BACKGROUND_SCALE),

    PLAYER_WIN_BAR(
            R.drawable.you_win_bar,
            416,
            96,
            2),

    PLAYER_LOSE_BAR(
            R.drawable.you_lose_bar,
            416,
            96,
            2),

    LEADERBOARD(
            R.drawable.leaderboard,
            224,
            320,
            2.7),

    CURRENT_BOARD(
            R.drawable.current_board,
            96,
            144,
            3.5),
    CONFIG_BOARD(
            R.drawable.config_ui_board,
            384,
            288,
            3);


    private int width;
    private int height;
    private final Bitmap image;

    GameImages(int resID, int width, int height, double scale) {
        OPTIONS.inScaled = false;
        Bitmap temp = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        image = Bitmap.createScaledBitmap(
                temp, (int) (width * scale),
                (int) (height * scale), false
        );

        this.width = (int) (width * scale);
        this.height = (int) (height * scale);
    }

    public Bitmap getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}