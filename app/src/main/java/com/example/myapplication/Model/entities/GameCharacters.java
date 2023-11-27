package com.example.myapplication.Model.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;

import com.example.myapplication.R;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.interfaces.BitmapMethods;
import com.example.myapplication.ViewModel.MainViewModel;

public enum GameCharacters implements BitmapMethods {


    WARRIOR(
            R.drawable.warrior_character_list,
            new PointF(GameConstants.CharacterDefault.WARRIOR_WIDTH,
            GameConstants.CharacterDefault.WARRIOR_HEIGHT),
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_X,
            GameConstants.CharacterDefault.WARRIOR_ANIMATION_Y,
            GameConstants.CharacterDefault.WARRIOR_SCALE,
            GameConstants.CharacterDefault.WARRIOR_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.WARRIOR_HITBOX_OFF_SET_Y
    ),

    WARRIOR2(
            R.drawable.new_warrior_anim_list,
            new PointF(64,
                    64),
            30,
            30,
            3.5,
            51,
            26
    ),
    WITCH2(
            R.drawable.new_witch_anim_list,
            new PointF(64,
                    64),
            30,
            30,
            3.5,
            46,
            30
    ),
    CENTAUR(
            R.drawable.centaur_anim_list,
            new PointF(64,
                    64),
            30,
            30,
            3.5,
            36,
            28
    ),

    WITCH(
            R.drawable.witch_character_list,
            new PointF(GameConstants.CharacterDefault.WITCH_WIDTH,
                    GameConstants.CharacterDefault.WITCH_HEIGHT),
            GameConstants.CharacterDefault.WITCH_ANIMATION_X,
            GameConstants.CharacterDefault.WITCH_ANIMATION_Y,
            GameConstants.CharacterDefault.WITCH_SCALE,
            GameConstants.CharacterDefault.WITCH_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.WITCH_HITBOX_OFF_SET_Y
    ),

    TERESA(
            R.drawable.teresa_character_list,
            new PointF(GameConstants.CharacterDefault.TERESA_WIDTH,
                    GameConstants.CharacterDefault.TERESA_HEIGHT),
            GameConstants.CharacterDefault.TERESA_ANIMATION_X,
            GameConstants.CharacterDefault.TERESA_ANIMATION_Y,
            GameConstants.CharacterDefault.TERESA_SCALE,
            GameConstants.CharacterDefault.TERESA_HITBOX_OFF_SET_X,
            GameConstants.CharacterDefault.TERESA_HITBOX_OFF_SET_Y
    ),

    ZOMBIE(
            R.drawable.zombie_mob_list,
            new PointF(GameConstants.MobAnimDefault.ZOMBIE_WIDTH,
                    GameConstants.MobAnimDefault.ZOMBIE_HEIGHT),
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_X,
            GameConstants.MobAnimDefault.ZOMBIE_ANIM_Y,
            6,
            2,
            4
    ),

    OGRE(
            R.drawable.ogre_warrior_anim_list,
            new PointF(96,
                    96),
            25,
            10,
            6,
            83,
            74
    ),

    MINOTAUR(
            R.drawable.minotaur_anim_list,
            new PointF(128, 128),
            20,
            10,
            6,
            114,
            100
    ),

    CHEST_MOB(
            R.drawable.chest_mob_list,
            new PointF(26, 34),
            4,
            4,
            6,
            6,
            8
    ),
    ROGUE_GOBLIN(
            R.drawable.rogue_goblin_anim_list,
            new PointF(48, 48),
            20,
            10,
            4,
            32,
            16
    ),


    CROW_MAN(
            R.drawable.crow_mob_list,
            new PointF(22, 30),
            4,
            4,
            6,
            4,
            7
    ),
    STEEL_GOLEM(
            R.drawable.golem_steel_anim_list,
            new PointF(96, 96),
            20,
            10,
            6,
            76,
            69
    );


    private Bitmap spriteSheet;
    private Bitmap[][] sprites;
    // 素材一共Y行 * X列格，即[Y][X]
    private int characterWidth;
    private int characterHeight;
    private double scale;
    private int maxAnimIndex;
    private int hitBoxOffSetX;
    private int hitBoxOffSetY;



    GameCharacters(
            int resID,
            PointF size,
            int animationX, int animationY,
            double scale,
            int hitBoxOffSetX, int hitBoxOffSetY
    ) {

        int width = (int) size.x;
        int height = (int) size.y;

        this.scale = scale;
        this.characterWidth = width;
        this.characterHeight = height;
        this.maxAnimIndex = animationX;
        this.hitBoxOffSetX = hitBoxOffSetX;
        this.hitBoxOffSetY = hitBoxOffSetY;

        OPTIONS.inScaled = false;

        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(
                MainViewModel.getGameContext().getResources(), resID, OPTIONS
        );
        for (int j = 0; j < sprites.length; j++) {
            for (int i = 0; i < sprites[j].length; i++) {
                Bitmap temp
                        =  Bitmap.createBitmap(
                                spriteSheet, width * i, height * j, width, height
                );
                sprites[j][i]
                        = Bitmap.createScaledBitmap(
                                temp, (int) (width * scale), (int) (height * scale), false
                );
            }
        }

    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }


    public int getHitBoxOffSetX() {
        return (int) (hitBoxOffSetX * scale);
    }

    public int getHitBoxOffSetY() {
        return (int) (hitBoxOffSetY * scale);
    }


    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos];
    } //y素材行数，x//列数

    public int getCharacterWidth() {
        return (int) (characterWidth * scale);
    }

    public int getCharacterHeight() {
        return (int) (characterHeight * scale);
    }

    public double getScale() {
        return scale;
    }


    public int getMaxAnimIndex() {
        return maxAnimIndex;
    }
}
