package com.example.myapplication.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myapplication.R;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.BitmapMethods;
import com.example.myapplication.main.MainActivity;

public enum GameCharacters implements BitmapMethods {


    ELF(R.drawable.elf_character_list, GameConstants.PlayerAnimDefault.ELF_WIDTH, GameConstants.PlayerAnimDefault.ELF_HEIGHT, GameConstants.PlayerAnimDefault.ELF_ANIM_X, GameConstants.PlayerAnimDefault.ELF_ANIM_Y,6),
    KNIGHT(R.drawable.knight_character_list, GameConstants.PlayerAnimDefault.KNIGHT_WIDTH, GameConstants.PlayerAnimDefault.KNIGHT_HEIGHT, GameConstants.PlayerAnimDefault.KNIGHT_ANIM_X, GameConstants.PlayerAnimDefault.KNIGHT_ANIM_Y,6),
    WIZARD(R.drawable.wizard_character_list, GameConstants.PlayerAnimDefault.WIZARD_WIDTH, GameConstants.PlayerAnimDefault.WIZARD_HEIGHT, GameConstants.PlayerAnimDefault.WIZARD_ANIM_X, GameConstants.PlayerAnimDefault.WIZARD_ANIM_Y,6),
    WARRIOR(R.drawable.warrior_character_list, 29, 33, 8, 4,6),
    ZOMBIE(R.drawable.zombie_mob_list, GameConstants.MobAnimDefault.ZOMBIE_WIDTH, GameConstants.MobAnimDefault.ZOMBIE_HEIGHT, GameConstants.MobAnimDefault.ZOMBIE_ANIM_X, GameConstants.MobAnimDefault.ZOMBIE_ANIM_Y,6);


    private Bitmap spriteSheet;
    private Bitmap[][] sprites; //
    // 素材一共Y行 * X列格，即[Y][X]


    GameCharacters(int resID, int width, int height, int animationX, int animationY, double scale) {
        options.inScaled = false;
        sprites = new Bitmap[animationY][animationX];
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resID, options);
        for(int j = 0; j < sprites.length; j++) {
            for(int i = 0; i < sprites[j].length; i++) {
                Bitmap temp =  Bitmap.createBitmap(spriteSheet, width * i, height * j, width, height);
                sprites[j][i] = Bitmap.createScaledBitmap(temp, (int) (width * scale), (int) (height * scale), false);
            }
        }

        //System.out.println("Width: " + spriteSheet.getWidth() + " Height: " + spriteSheet.getHeight());
    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public Bitmap getSprite(int yPos, int xPos) {
         return sprites[yPos][xPos];
    } //y素材行数，x//列数

}
