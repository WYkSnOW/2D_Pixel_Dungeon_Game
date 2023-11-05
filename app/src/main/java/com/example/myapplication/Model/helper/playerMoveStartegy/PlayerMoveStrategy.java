package com.example.myapplication.Model.helper.playerMoveStartegy;

import android.graphics.PointF;

public interface PlayerMoveStrategy {
    abstract void setPlayerAnim(float xSpeed, float ySpeed, PointF lastTouchDiff);
    abstract PointF playerMovement(float xSpeed, float ySpeed, float baseSpeed);
}
