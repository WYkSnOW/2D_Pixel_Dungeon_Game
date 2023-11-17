package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

public class RogueGoblinStrategy implements EnemyStrategy {
    @Override
    public int getAnimMaxIndex(EnemyStates state) {
        if (state == EnemyStates.WALK) {
            return 10;
        } else if (state == EnemyStates.IDLE) {
            return 11;
        } else if (state == EnemyStates.ATK) {
            return 14;
        } else if (state == EnemyStates.HURT) {
            return 6;
        } else if (state == EnemyStates.DEATH) {
            return 17;
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
            offset = 7;
        } else if (states == EnemyStates.IDLE) {
            offset = 11;
        } else if (states == EnemyStates.ATK) {
            offset = 17;
        } else if (states == EnemyStates.HURT) {
            offset = 7;
        } else if (states == EnemyStates.DEATH) {
            offset = 10;
        }
        return offset * scale;
    }

    @Override
    public float adjustY(EnemyStates states, float scale) {
        float offset = 0;
        if (states == EnemyStates.ATK) {
            offset = 2;
        } else if (states == EnemyStates.DEATH) {
            offset = 7;
        }
        return offset * scale;
    }

    @Override
    public boolean isMakingDamage(EnemyStates state, int idx) {
        if (state == EnemyStates.ATK) {
            return (4 <= idx && idx <= 5) || (9 <= idx && idx <= 10);
        }
        return false;
    }
}
