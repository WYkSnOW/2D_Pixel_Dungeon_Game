package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.ChestMobStrategy;

public class ChestMob extends AbstractEnemy {
    public ChestMob(PointF pos) {
        super(pos, GameCharacters.CHEST_MOB, 100, 3, 50, new ChestMobStrategy());
    }


    @Override
    int getDefaultChaseDis() {
        return 4;
    }
}
