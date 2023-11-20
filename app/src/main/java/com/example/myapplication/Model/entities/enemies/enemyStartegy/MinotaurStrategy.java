package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;
import com.example.myapplication.Model.helper.GameConstants;

public class MinotaurStrategy implements EnemyStrategy {
    @Override
    public int getAnimMaxIndex(EnemyStates state) {
        if (state == EnemyStates.WALK) {
            return 10;
        } else if (state == EnemyStates.IDLE) {
            return 4;
        } else if (state == EnemyStates.ATK) {
            return 13;
        } else if (state == EnemyStates.HURT) {
            return 4;
        } else if (state == EnemyStates.DEATH) {
            return 16;
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
            offset = 35;
        } else if (states == EnemyStates.IDLE) {
            offset = 37;
        } else if (states == EnemyStates.ATK) {
            offset = 73;
        } else if (states == EnemyStates.HURT) {
            offset = 36;
        } else if (states == EnemyStates.DEATH) {
            offset = 36;
        }
        return offset * scale;
    }

    @Override
    public float adjustY(EnemyStates states, float scale) {
        float offset = 0;
        if (states == EnemyStates.ATK) {
            offset = 12;
        }
        return offset * scale;
    }

    @Override
    public PointF getAtkDetectSize() {
        return new PointF(GameConstants.Sprite.SIZE * 1.3f, GameConstants.Sprite.SIZE);
    }

    @Override
    public PointF getAtkHitBoxSize() {
        return new PointF(GameConstants.Sprite.SIZE * 3f, GameConstants.Sprite.SIZE);
    }

    @Override
    public boolean isMakingDamage(EnemyStates state, int idx) {
        if (state == EnemyStates.ATK) {
            return (6 <= idx && idx <= 8);
        }
        return false;
    }
}
