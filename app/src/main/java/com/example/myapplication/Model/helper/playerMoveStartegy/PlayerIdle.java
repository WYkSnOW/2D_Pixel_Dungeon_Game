package com.example.myapplication.Model.helper.playerMoveStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.Player.Player;

public class PlayerIdle implements PlayerMoveStrategy {
    @Override
    public void setPlayerAnim(float xSpeed, float ySpeed, PointF lastTouchDiff) {
        if (Player.getInstance().getDrawDir() <= 1) {
            Player.getInstance().setDrawDir(Player.getInstance().getDrawDir() + 2);
        }
    }

    @Override
    public PointF playerMovement(float xSpeed, float ySpeed, float baseSpeed) {
        return new PointF(0,0);
    }
}
