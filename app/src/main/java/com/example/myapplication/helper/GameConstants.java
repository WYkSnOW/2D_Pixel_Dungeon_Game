package com.example.myapplication.helper;

public final class GameConstants {
    public static final class Face_Dir{
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
        public static final int IDLE = 4;

    }



    public static final class Sprite {
        public static final int DEFAULT_SIZE = 16;
        public static final int SCALE_MULTIPLIER = 6;
        public static final int SIZE = DEFAULT_SIZE * SCALE_MULTIPLIER;
    }

    public static final class Animation {
        public static final int ANI_SPEED = 10;
        public static final int AMOUNT = 4; //每种动画共有4帧
    }
}
