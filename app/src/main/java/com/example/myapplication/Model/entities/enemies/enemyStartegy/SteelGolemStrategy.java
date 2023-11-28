package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;
import com.example.myapplication.Model.helper.GameConstants;

public class SteelGolemStrategy implements EnemyStrategy {
    @Override
    public int getAnimMaxIndex(EnemyStates state) {
        if (state == EnemyStates.WALK) {
            return 13;
        } else if (state == EnemyStates.IDLE) {
            return 9;
        } else if (state == EnemyStates.ATK) {
            return 20;
        } else if (state == EnemyStates.HURT) {
            return 4;
        } else if (state == EnemyStates.DEATH) {
            return 20;
        }

        return 1;
    }

    @Override
    public PointF getAtkBoxSize() {
        return null;
    }

    @Override
    public float adjustX(EnemyStates states, float scale) {
        float offset = 0;
        if (states == EnemyStates.WALK) {
            offset = 12;
        } else if (states == EnemyStates.IDLE) {
            offset = 12;
        } else if (states == EnemyStates.ATK) {
            offset = 32;
        } else if (states == EnemyStates.HURT) {
            offset = 12;
        } else if (states == EnemyStates.DEATH) {
            offset = 12;
        }
        return offset * scale;
    }

    @Override
    public float adjustY(EnemyStates states, float scale) {
        float offset = 0;
        if (states == EnemyStates.ATK) {
            offset = 2;
        }
        return offset * scale;
    }

    @Override
    public PointF getAtkDetectSize() {
        return new PointF(GameConstants.Sprite.SIZE, GameConstants.Sprite.SIZE);
    }

    @Override
    public PointF getAtkHitBoxSize() {
        return new PointF(GameConstants.Sprite.SIZE * 2, GameConstants.Sprite.SIZE);
    }

    @Override
    public boolean isMakingDamage(EnemyStates state, int idx) {
        if (state == EnemyStates.ATK) {
            return (13 <= idx && idx <= 16);
        }
        return false;
    }

    @Override
    public int getScore() {
        return 50;
    }
}
