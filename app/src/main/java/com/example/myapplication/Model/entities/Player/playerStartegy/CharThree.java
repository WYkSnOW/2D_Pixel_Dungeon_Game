package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;

public class CharThree implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth((int) (1.2 * GameConstants.Sprite.SIZE));
        Player.getInstance().setAttackHeight(2 * GameConstants.Sprite.SIZE);
    }

    @Override
    public void initAtkEffect() {
        Player.getInstance().setAttackAffect(null);
    }
    @Override
    public void initChar() {
        Player.getInstance().setCharacterChoice(GameCharacters.WARRIOR);
    }

    @Override
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 8;
        } else if (state == PlayerStates.RUNNING) {
            return 8;
        } else if (state == PlayerStates.ATTACK) {
            return 8;
        }
        return 1;
    }

    @Override
    public void drawAttackBox(Canvas c) {
        c.drawRect(
                Player.getInstance().getAttackBox(),
                Player.getInstance().getHitBoxPaint()
        ); //draw weapon's hitbox
    }
}
