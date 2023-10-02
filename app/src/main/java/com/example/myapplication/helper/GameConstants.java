package com.example.myapplication.helper;

public final class GameConstants {
    public static final class FaceDir {
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
        public static final int IDLE = 4;

    }

    public static final class DrawDir {
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int IDLE_RIGHT = 2;
        public static final int IDLE_LEFT = 3;

    }

    public static final class PlayerAnimDefault {
        public static final int ELF_WIDTH = 16;
        public static final int ELF_HEIGHT = 16;
        public static final int ELF_ANIM_X = 4;
        public static final int ELF_ANIM_Y = 4;

        public static final int KNIGHT_WIDTH = 16;
        public static final int KNIGHT_HEIGHT = 22;
        public static final int KNIGHT_ANIM_X = 4;
        public static final int KNIGHT_ANIM_Y = 4;

        public static final int WIZARD_WIDTH = 16;
        public static final int WIZARD_HEIGHT = 21;
        public static final int WIZARD_ANIM_X = 4;
        public static final int WIZARD_ANIM_Y = 4;
    }


    public static final class MobAnimDefault {
        public static final int ZOMBIE_WIDTH = 20;
        public static final int ZOMBIE_HEIGHT = 33;
        public static final int ZOMBIE_ANIM_X = 4;
        public static final int ZOMBIE_ANIM_Y = 4;
    }

    public static final class LoopVideo {
        public static final int BACK_VIDEO_WIDTH = 1920;
        public static final int BACK_VIDEO_HEIGHT = 1080;
        public static final int MAIN_BACK_MAX_INDEX = 8;
        public static final int MAIN_BACK_MAX_ROW = 0;
        public static final int CONFIG_BACK_MAX_INDEX = 11;
        public static final int CONFIG_BACK_MAX_ROW = 0;
    }



    public static final class Sprite {
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALE_MULTIPLIER = 6;
        public static final int SIZE = DEFAULT_SIZE * SCALE_MULTIPLIER;
    }

    public static final class Animation {
        public static final int ANI_SPEED = 7;
        public static final int AMOUNT = 4; //每种动画共有4帧
    }
}
