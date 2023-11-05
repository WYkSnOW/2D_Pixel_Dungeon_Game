package com.example.myapplication.Model.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

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
            GameConstants.UiSize.UI_HOLDER1_WIDTH,
            GameConstants.UiSize.UI_HOLDER1_HEIGHT,
            false,
            12
    ),

    CONFIG_BTN_RIGHT(
            R.drawable.config_btn_right,
            26,
            30,
            true,
            3
    ),
    CONFIG_BTN_LEFT(
            R.drawable.config_btn_left,
            26,
            30,
            true,
            3
    ),

    CONFIG_BTN_SELECT(
            R.drawable.config_btn_select,
            64,
            32,
            true,
            3
    );



    private int width;
    private int height;
    private int scale;

    private Bitmap normal;
    private Bitmap pushed;
    ButtonImage(int resID, int width, int height, boolean haveAnim, int scale) {
        OPTIONS.inScaled = false;
        this.height = height;
        this.width = width;
        this.scale = scale;

        Bitmap buttonAtlas
                = BitmapFactory.decodeResource(
                        MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
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
