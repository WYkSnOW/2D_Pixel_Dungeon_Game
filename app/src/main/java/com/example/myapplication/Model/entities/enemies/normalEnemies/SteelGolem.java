package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.SteelGolemStrategy;
import com.example.myapplication.Model.entities.enemies.normalEnemies.AbstractEnemy;

public class SteelGolem extends AbstractEnemy {

    public SteelGolem(PointF pos) {
        super(pos, GameCharacters.STEEL_GOLEM, 70, 5,  100, new SteelGolemStrategy());
    }

    @Override
    int getDefaultChaseDis() {
        return 5;
    }
}
