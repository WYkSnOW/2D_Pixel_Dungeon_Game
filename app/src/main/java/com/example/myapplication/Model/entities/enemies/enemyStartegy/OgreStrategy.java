package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;
import com.example.myapplication.Model.helper.GameConstants;

public class OgreStrategy implements EnemyStrategy {
    @Override
    public int getAnimMaxIndex(EnemyStates state) {
        if (state == EnemyStates.WALK) {
            return 11;
        } else if (state == EnemyStates.IDLE) {
            return 6;
        } else if (state == EnemyStates.ATK) {
            return 11;
        } else if (state == EnemyStates.HURT) {
            return 6;
        } else if (state == EnemyStates.DEATH) {
            return 21;
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
            offset = 17;
        } else if (states == EnemyStates.IDLE) {
            offset = 17;
        } else if (states == EnemyStates.ATK) {
            offset = 42;
        } else if (states == EnemyStates.HURT) {
            offset = 17;
        } else if (states == EnemyStates.DEATH) {
            offset = 24;
        }
        return offset * scale;
    }

    @Override
    public float adjustY(EnemyStates states, float scale) {
        float offset = 0;
        if (states == EnemyStates.IDLE) {
            offset = 2;
        } else if (states == EnemyStates.ATK) {
            offset = 3;
        } else if (states == EnemyStates.HURT) {
            offset = 2;
        } else if (states == EnemyStates.DEATH) {
            offset = 2;
        }
        return offset * scale;
    }

    @Override
    public PointF getAtkDetectSize() {
        return new PointF(GameConstants.Sprite.SIZE * 0.5f, GameConstants.Sprite.SIZE);
    }

    @Override
    public PointF getAtkHitBoxSize() {
        return new PointF(GameConstants.Sprite.SIZE * 1.3f, GameConstants.Sprite.SIZE);
    }

    @Override
    public boolean isMakingDamage(EnemyStates state, int idx) {
        if (state == EnemyStates.ATK) {
            return (4 <= idx && idx <= 7);
        }
        return false;
    }

    @Override
    public int getScore() {
        return 20;
    }
}
