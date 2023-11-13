package com.example.myapplication.Model.loopVideo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameVideos implements BitmapMethods {


    MAIN_BACK_VIDEO(
            R.drawable.main_back_video,
            GameConstants.VideosDefault.BACKGROUND_WIDTH,
            GameConstants.VideosDefault.BACKGROUND_HEIGHT,
            GameConstants.VideosDefault.MAIN_BACKGROUND_ANIMATIONX,
            GameConstants.VideosDefault.MAIN_BACKGROUND_ANIMATIONY,
            GameConstants.VideosDefault.BACKGROUND_SCALE,
            GameConstants.VideosDefault.MAIN_BACKGROUND_ANIM_RATE
    ),
    DIFFICULTY_BOX(
            R.drawable.difficulty_box,
            GameConstants.VideosDefault.DIFFICULTY_BOX_WIDTH,
            GameConstants.VideosDefault.DIFFICULTY_BOX_HEIGHT,
            GameConstants.VideosDefault.DIFFICULTY_BOX_ANIMATIONX,
            GameConstants.VideosDefault.DIFFICULTY_BOX_ANIMATIONY,
            GameConstants.VideosDefault.DIFFICULTY_BOX_SCALE,
            60

    ),

    START_DATA(
            R.drawable.character_start_date,
            144,
            118,
            3,
            1,
            3,
            60

    ),
    WITCH(
            R.drawable.witch_character_list,
            GameConstants.CharacterDefault.WITCH_WIDTH,
            GameConstants.CharacterDefault.WITCH_HEIGHT,
            GameConstants.CharacterDefault.WITCH_ANIMATION_X,
            GameConstants.CharacterDefault.WITCH_ANIMATION_Y,
            GameConstants.VideosDefault.WITCH_SCALE,
            GameConstants.VideosDefault.WITCH_ANIM_RATE
    ),
    WITCH_ATTACK_EFFECT(
            R.drawable.witch_attack_effect,
            GameConstants.VideosDefault.WITCH_EFFECT_WIDTH,
            GameConstants.VideosDefault.WITCH_EFFECT_HEIGHT,
            GameConstants.VideosDefault.WITCH_EFFECT_ANIMATIONX,
            GameConstants.VideosDefault.WITCH_EFFECT_ANIMATIONY,
            GameConstants.VideosDefault.WITCH_EFFECT_SCALE,
            GameConstants.VideosDefault.WITCH_EFFECT_ANIM_RATE

    ),
    TERESA(
            R.drawable.teresa_character_list,
            GameConstants.CharacterDefault.TERESA_WIDTH,
            GameConstants.CharacterDefault.TERESA_HEIGHT,
            GameConstants.CharacterDefault.TERESA_ANIMATION_X,
            GameConstants.CharacterDefault.TERESA_ANIMATION_Y,
            GameConstants.VideosDefault.TERESA_SCALE,
            GameConstants.VideosDefault.TERESA_RATE
    ),

    WARRIOR(
            R.drawable.warrior_character_list,
            GameConstants.CharacterDefault.WARRIOR_WIDTH,
            GameConstants.CharacterDefault.WARRIOR_HEIGHT,
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_X,
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_Y,
            GameConstants.VideosDefault.WARRIOR_SCALE,
            GameConstants.VideosDefault.WARRIOR_RATE
    ),

    CONFIG_BACK_VIDEO(
            R.drawable.config_back_video,
            GameConstants.VideosDefault.BACKGROUND_WIDTH,
            GameConstants.VideosDefault.BACKGROUND_HEIGHT,
            GameConstants.VideosDefault.CONFIG_BACKGROUND_ANIMATIONX,
            GameConstants.VideosDefault.CONFIG_BACKGROUND_ANIMATIONY,
            GameConstants.VideosDefault.BACKGROUND_SCALE,
            GameConstants.VideosDefault.CONFIG_BACKGROUND_ANIM_RATE
    ),

    END_BACK_VIDEO(
            R.drawable.end_screen_back_video,
            GameConstants.VideosDefault.BACKGROUND_WIDTH,
            GameConstants.VideosDefault.BACKGROUND_HEIGHT,
            22,
            1,
            GameConstants.VideosDefault.BACKGROUND_SCALE,
            8
    ),

    LOSE_SCREEN_VIDEO(
            R.drawable.lose_screen_video,
            GameConstants.VideosDefault.BACKGROUND_WIDTH,
            GameConstants.VideosDefault.BACKGROUND_HEIGHT,
            11,
            1,
            GameConstants.VideosDefault.BACKGROUND_SCALE * 2,
            8
    ),

    ARROW_ANIM(
            R.drawable.arrow_anim_list,
            46,
            5,
            4,
            2,
            3.5,
            GameConstants.Animation.ANI_SPEED
    ),

    FIRE_BALL_ANIM(
            R.drawable.fire_ball_anim_list,
            128,
            128,
            4,
            2,
            3.5,
            GameConstants.Animation.ANI_SPEED
    ),
    SWORD_PJT_ANIM(
            R.drawable.sword_pjt_anim_list,
            32,
            32,
            8,
            2,
            6,
            GameConstants.Animation.ANI_SPEED
    ),
    STORM_ARROW_ANIM(
            R.drawable.storm_arrow_anim_list,
            256,
            96,
            4,
            2,
            2,
            GameConstants.Animation.ANI_SPEED
    ),

    BLACK_HOLE_ANIM(
            R.drawable.black_hole_anim_list,
            148,
            128,
            23,
            2,
            3.5,
            GameConstants.Animation.ANI_SPEED
    ),

    PLAYER_HEALTH_BAR(
            R.drawable.player_health_bar_blank,
            157,
            18,
            1,
            1,
            5,
            GameConstants.Animation.ANI_SPEED
    ),
    PLAYER_HEALTH_START(
            R.drawable.health_red_bar_start_img,
            6,
            18,
            3,
            1,
            5,
            GameConstants.Animation.ANI_SPEED
    ),


    PLAYER_HEALTH_MIDDLE(
            R.drawable.health_red_bar_middle_img,
            2,
            18,
            8,
            1,
            5,
            GameConstants.Animation.ANI_SPEED
    ),

    PLAYER_HEALTH_END(
            R.drawable.health_red_bars_end,
            2,
            18,
            8,
            1,
            5,
            GameConstants.Animation.ANI_SPEED
    ),

    PLAYER_HEALTH_END_ANIM(
            R.drawable.health_anim_bar,
            16,
            18,
            8,
            1,
            5,
            GameConstants.Animation.ANI_SPEED
    ),


    MOB_HEALTH_BAR(
            R.drawable.mob_health_bar_blank,
            52,
            4,
            1,
            1,
            3,
            GameConstants.Animation.ANI_SPEED
    ),

    MOB_HEALTH(
            R.drawable.mob_health,
            1,
            4,
            1,
            1,
            3,
            GameConstants.Animation.ANI_SPEED
    );

    private final Bitmap spriteSheet;
    private final Bitmap[][] sprites;
    private int maxAnimIndex;

    private int width;
    private int height;
    private double scale;
    private int animRate;

    //contain all video resource that is being use
    GameVideos(
            int resID, int width, int height,
            int animationX, int animationY,
            double scale, int animRate
    ) {
        OPTIONS.inScaled = false;
        this.width = width;
        this.height = height;
        this.scale =  scale;
        this.maxAnimIndex = animationX;
        this.animRate = animRate;


        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp = Bitmap.createBitmap(
                        spriteSheet, width * i, height * j, width, height
                );
                sprites[j][i] = Bitmap.createScaledBitmap(
                        temp, (int) (width * scale), (int) (height * scale), false
                );

            }
        }

    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public int getMaxAnimIndex() {
        return maxAnimIndex;
    }
    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos]; //y is row in asset，x is list in asset
    }

    public int getWidth() {
        return (int) (width * scale);
    }

    public int getHeight() {
        return (int) (height * scale);
    }

    public double getScale() {
        return scale;
    }

    public int getAnimRate() {
        return animRate;
    }
}