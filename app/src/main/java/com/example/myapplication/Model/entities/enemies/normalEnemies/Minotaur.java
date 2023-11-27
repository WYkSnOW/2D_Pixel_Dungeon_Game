package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.MinotaurStrategy;

public class Minotaur extends AbstractEnemy {
    public Minotaur(PointF pos) {
        super(pos, GameCharacters.MINOTAUR, 50, 10, 200, new MinotaurStrategy());
    }


    @Override
    int getDefaultChaseDis() {
        return 4;
    }
}
