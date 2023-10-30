package com.example.myapplication.Model.entities.enemies;


import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;


public class Zombie extends AbstractEnemy {
    public Zombie(PointF pos) {
        super(pos, GameCharacters.ZOMBIE, 150, 2, 30);
    }


    @Override
    int getDefaultChaseDis() {
        return 4;
    }
}
