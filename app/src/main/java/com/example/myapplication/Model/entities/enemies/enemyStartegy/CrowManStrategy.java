package com.example.myapplication.Model.entities.enemies.enemyStartegy;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

public class CrowManStrategy implements EnemyStrategy {
    @Override
    public int getAnimMaxIndex(EnemyStates state) {
        return 4;
    }

    @Override
    public PointF getAtkBoxSize() {
        return null;
    }

    @Override
    public float adjustX(EnemyStates states, float scale) {
        return 0;
    }

    @Override
    public float adjustY(EnemyStates states, float scale) {
        return 0;
    }
}
