package com.example.myapplication.Model.entities.enemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;

public class SteelGolem extends AbstractEnemy {

    public SteelGolem(PointF pos) {
        super(pos, GameCharacters.STEEL_GOLEM, 50, 5,  100);
    }

    @Override
    int getDefaultChaseDis() {
        return 5;
    }
}
