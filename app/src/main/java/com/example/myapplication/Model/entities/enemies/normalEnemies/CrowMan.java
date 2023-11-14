package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.CrowManStrategy;

public class CrowMan extends AbstractEnemy {
    public CrowMan(PointF pos) {
        super(pos, GameCharacters.CROW_MAN, 200, 1, 20, new CrowManStrategy());
    }

    @Override
    int getDefaultChaseDis() {
        return 5;
    }
}
