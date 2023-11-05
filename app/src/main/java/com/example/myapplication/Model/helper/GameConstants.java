package com.example.myapplication.Model.helper;

import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.ViewModel.MainViewModel;

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

    public static final class CharacterDefault {
        public static final int WARRIOR_WIDTH = 55;
        public static final int WARRIOR_HEIGHT = 42;
        public static final int WARRIOR_ANIMATION_X = 8;
        public static final int WARRIOR_ANIMATION_Y = 6;
        public static final int WARRIOR_SCALE = 4;
        public static final int WARRIOR_HITBOX_OFF_SET_X = 24;
        public static final int WARRIOR_HITBOX_OFF_SET_Y = 13;

        //for Witch
        public static final int WITCH_WIDTH = 43;
        public static final int WITCH_HEIGHT = 47;
        public static final int WITCH_ANIMATION_X = 6;
        public static final int WITCH_ANIMATION_Y = 6;
        public static final int WITCH_SCALE = 3;
        public static final int WITCH_HITBOX_OFF_SET_X = 11;
        public static final int WITCH_HITBOX_OFF_SET_Y = 14;

        //for Teresa
        public static final int TERESA_WIDTH = 43;
        public static final int TERESA_HEIGHT = 40;
        public static final int TERESA_ANIMATION_X = 8;
        public static final int TERESA_ANIMATION_Y = 6;
        public static final int TERESA_SCALE = 4;
        public static final int TERESA_HITBOX_OFF_SET_X = 19;
        public static final int TERESA_HITBOX_OFF_SET_Y = 13;
    }

    public static final class ImageDefault {
        public static final int CLOUD_BACKGROUND_WIDTH = 370;
        public static final int CLOUD_BACKGROUND_HEIGHT = 330;
        public static final double CLOUD_BACKGROUND_SCALE
                = MainViewModel.getScaleRatio(CLOUD_BACKGROUND_WIDTH, CLOUD_BACKGROUND_HEIGHT);
    }

    public static final class VideosDefault {
        public static final int BACKGROUND_WIDTH = 1920;
        public static final int BACKGROUND_HEIGHT = 1080;
        public static final double BACKGROUND_SCALE
                = MainViewModel.getScaleRatio(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);


        public static final int MAIN_BACKGROUND_ANIMATIONX = 9;
        public static final int MAIN_BACKGROUND_ANIMATIONY = 1;
        public static final int MAIN_BACKGROUND_ANIM_RATE = 8;


        public static final int CONFIG_BACKGROUND_ANIMATIONX = 6;
        public static final int CONFIG_BACKGROUND_ANIMATIONY = 1;
        public static final int CONFIG_BACKGROUND_ANIM_RATE = 10;

        public static final int DIFFICULTY_BOX_WIDTH = 96;
        public static final int DIFFICULTY_BOX_HEIGHT = 96;
        public static final int DIFFICULTY_BOX_ANIMATIONX = 4;
        public static final int DIFFICULTY_BOX_ANIMATIONY = 1;
        public static final double DIFFICULTY_BOX_SCALE = 3.0;
        public static final int DIFFICULTY_BOX_ANIM_RATE = 4;

        public static final int WITCH_EFFECT_WIDTH = 86;
        public static final int WITCH_EFFECT_HEIGHT = 47;
        public static final int WITCH_EFFECT_ANIMATIONX = 6;
        public static final int WITCH_EFFECT_ANIMATIONY = 1;
        public static final double WITCH_EFFECT_SCALE = 3.0;
        public static final int WITCH_EFFECT_ANIM_RATE = 10;

        public static final double WITCH_SCALE = 6.0;
        public static final int WITCH_ANIM_RATE = 6;

        public static final double TERESA_SCALE = 7.0;
        public static final int TERESA_RATE = 6;

        public static final double WARRIOR_SCALE = 7.0;
        public static final int WARRIOR_RATE = 6;

    }

    public static final class Videos {
        //in config screen
        public static final int PICK_TERESA_POS_X = 100;
        public static final int PICK_TERESA_POS_Y
                = UiSize.GAME_HEIGHT - GameVideos.TERESA.getHeight() - 10;
        public static final int PICK_WITCH_POS_X
                = PICK_TERESA_POS_X + 300;
        public static final int PICK_WITCH_POS_Y
                = UiSize.GAME_HEIGHT - GameVideos.WITCH.getHeight();
        public static final int PICK_WARRIOR_POS_X
                = PICK_WITCH_POS_X + 300;
        public static final int PICK_WARRIOR_POS_Y
                = UiSize.GAME_HEIGHT - GameVideos.WARRIOR.getHeight() - 10;
    }

    public static final class Sprite {
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALE_MULTIPLIER = 6;
        public static final int DOUBLE_SCALE_MULTIPLIER = 12;
        public static final int SIZE = DEFAULT_SIZE * SCALE_MULTIPLIER;

    }

    public static final class Animation {
        public static final int ANI_SPEED = 6;
    }

    public static final class UiLocation {


        //in Menu
        //for start btn
        public static final int START_BTN_POS_X
                = UiSize.GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
        public static final int START_BTN_POS_Y
                = UiSize.GAME_HEIGHT - ButtonImage.MENU_START.getHeight() - 10;
        //for exit btn
        public static final int EXIT_BTN_POS_X
                = UiSize.GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
        public static final int EXIT_BTN_POS_Y = 10;

        //in ConfigScreen
        //for config btn
        public static final int CONFIG_START_BTN_POS_X
                = UiSize.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
        public static final int CONFIG_START_BTN_POS_Y
                = UiSize.GAME_HEIGHT - ButtonImage.MENU_START.getHeight();
        //for nameBar
        public static final int NAME_BAR_POS_X
                = UiSize.GAME_WIDTH / 2 - (UiSize.UI_HOLDER1_WIDTH * 12 / 2);
        public static final int NAME_BAR_POS_Y = UiSize.GAME_HEIGHT / 2;
        //for name text
        public static final int NAME_TEXT_POS_X
                = NAME_BAR_POS_X + (UiSize.UI_BAR1_WIDTH * 15 / 2);
        public static final int NAME_TEXT_POS_Y = NAME_BAR_POS_Y + 100;
        //for remainder text
        public static final int HINT_TEXT_POS_Y = NAME_TEXT_POS_Y + 100;
        //for difficulty box
        public static final int DIFFICULTY_BOX_POS_X
                = UiSize.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
        public static final int DIFFICULTY_BOX_POS_Y = UiSize.GAME_HEIGHT / 3;

        //for End Screen
        public static final int END_RESTART_BTN_POS_X
                = UiSize.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
        public static final int END_RESTART_BTN_POS_Y
                = UiSize.GAME_HEIGHT - ButtonImage.MENU_START.getHeight();
    }

    public static final class UiSize {
        public static final int GAME_WIDTH = MainViewModel.getGameWidth();
        public static final int GAME_HEIGHT = MainViewModel.getGameHeight();
        public static final int UI_BAR1_WIDTH = 44;
        public static final int UI_BAR1_HEIGHT = 12;
        public static final int UI_HOLDER1_WIDTH = 42;
        public static final int UI_HOLDER1_HEIGHT = 9;


        //public static final int
    }
}
