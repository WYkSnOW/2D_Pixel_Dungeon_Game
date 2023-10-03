package com.example.myapplication.Model.helper;

import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.View.main.MainActivity;

public final class GameConstants {
    public static final class MoveDir { //use to check collision
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;

    }

    public static final class DrawDir { //use to change animation
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int IDLE_RIGHT = 2;
        public static final int IDLE_LEFT = 3;
    }

    public static final class FaceDir { //tracking current face dircton
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
    }



    public static final class MobAnimDefault {
        public static final int ZOMBIE_WIDTH = 20;
        public static final int ZOMBIE_HEIGHT = 33;
        public static final int ZOMBIE_ANIM_X = 4;
        public static final int ZOMBIE_ANIM_Y = 4;
    }

    public static final class BackgroundVideos {

    }

    public static final class Sprite {
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALE_MULTIPLIER = 6;
        public static final int DOUBLE_SCALE_MULTIPLIER = 12;
        public static final int SIZE = DEFAULT_SIZE * SCALE_MULTIPLIER;

    }

    public static final class Animation {
        public static final int ANI_SPEED = 8;
    }

    public static final class UiLocation {
        public static final int GAME_WIDTH = MainActivity.gameWidth;
        public static final int GAME_HEIGHT = MainActivity.gameHeight;
        public static final double BACKGROUND_RATIO = MainActivity.getScaleRatio();

        //in Menu
        //for start btn
        public static final int START_BTN_POS_X = GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
        public static final int START_BTN_POS_Y = GAME_HEIGHT - ButtonImage.MENU_START.getHeight() - 10;
        //for exit btn
        public static final int EXIT_BTN_POS_X = GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
        public static final int EXIT_BTN_POS_Y = 10;

        //in ConfigScreen
        //for config btn
        public static final int CONFIG_START_BTN_POS_X = GAME_WIDTH - ButtonImage.MENU_START.getWidth();
        public static final int CONFIG_START_BTN_POS_Y = GAME_HEIGHT - ButtonImage.MENU_START.getHeight();
        //for nameBar
        public static final int NAME_BAR_POS_X = GAME_WIDTH / 2 - (UiSize.UI_Holder1_WIDTH * 15 / 2);
        public static final int NAME_BAR_POS_Y = GAME_HEIGHT / 2;
        //for name text
        public static final int NAME_TEXT_POS_X = NAME_BAR_POS_X + (UiSize.UI_BAR1_WIDTH * 15 / 2);
        public static final int NAME_TEXT_POS_Y = NAME_BAR_POS_Y + 100;
    }

    public static final class UiSize {

        public static final int UI_BAR1_WIDTH = 44;
        public static final int UI_BAR1_HEIGHT = 12;
        public static final int UI_Holder1_WIDTH = 42;
        public static final int UI_Holder1_HEIGHT = 9;


        //public static final int
    }
}
