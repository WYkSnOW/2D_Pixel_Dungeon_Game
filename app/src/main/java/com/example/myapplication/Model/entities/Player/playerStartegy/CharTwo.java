package com.example.myapplication.Model.entities.Player.playerStartegy;

import android.graphics.Canvas;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.loopVideo.GameVideos;

public class CharTwo implements PlayerCharStrategy {
    @Override
    public void initCharAtkBoxInfo() {
        Player.getInstance().setAttackWidth(GameVideos.WITCH_ATTACK_EFFECT.getWidth());
        Player.getInstance().setAttackHeight(GameConstants.Sprite.SIZE);
    }

    @Override
    public void initAtkEffect() {
        Player.getInstance().setAttackAffect(
                new GameAnimation(
                        1,
                        1,
                        1,
                        1,
                        GameVideos.WITCH_ATTACK_EFFECT
                ));
    }
    @Override
    public void initChar() {
        Player.getInstance().setCharacterChoice(GameCharacters.WITCH);
    }

    @Override
    public int getAnimMaxIndex(PlayerStates state) {
        if (state == PlayerStates.IDLE) {
            return 6;
        } else if (state == PlayerStates.RUNNING) {
            return 6;
        } else if (state == PlayerStates.ATTACK) {
            return 6;
        }
        return 1;
    }

    @Override
    public void drawAttackBox(Canvas c) {
        c.rotate(
                getEffectRote(),
                Player.getInstance().getAttackBox().left,
                Player.getInstance().getAttackBox().top);


        c.drawBitmap(
                Player.getInstance().getAttackAffect().getGameVideoType().getSprite(
                        0,
                        Player.getInstance().getAttackAffect().getAniIndex()
                ),
                Player.getInstance().getAttackBox().left + attackEffectAdjustLeft(),
                Player.getInstance().getAttackBox().top + attackEffectAdjustTop(),
                null
        );
        c.rotate(
                getEffectRote() * -1,
                Player.getInstance().getAttackBox().left,
                Player.getInstance().getAttackBox().top
        ); //rotate back

        c.drawRect(
                Player.getInstance().getAttackBox(),
                Player.getInstance().getHitBoxPaint()
        ); //draw weapon's hitbox
    }

    private float getEffectRote() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.LEFT) {
            return 180;
        } else {
            return 0;
        }
    }
    //adjust make base on that default direction of weapon is facing downward
    private float attackEffectAdjustTop() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            int effectAdjustTopWhenRight = -30;
            return effectAdjustTopWhenRight;
        } else {
            int effectAdjustTopWhenLeft = -115;
            return effectAdjustTopWhenLeft;
        }
    }
    private float attackEffectAdjustLeft() {
        if (Player.getInstance().getFaceDir() == GameConstants.FaceDir.RIGHT) {
            int effectAdjustLeftWhenRight = 20;
            return effectAdjustLeftWhenRight;
        } else {
            int effectAdjustLeftWhenLeft = -240;
            return effectAdjustLeftWhenLeft;
        }
    }
}
