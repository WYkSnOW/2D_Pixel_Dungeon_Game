package com.example.myapplication.Model.entities.enemies.normalEnemies;


import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.ZombieStrategy;
import com.example.myapplication.Model.entities.enemies.normalEnemies.AbstractEnemy;


public class Zombie extends AbstractEnemy {
    public Zombie(PointF pos) {
        super(pos, GameCharacters.ZOMBIE, 150, 2, 30, new ZombieStrategy());
    }


    @Override
    int getDefaultChaseDis() {
        return 4;
    }
}
