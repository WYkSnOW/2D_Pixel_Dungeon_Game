package com.example.myapplication.Model.entities.enemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;

public class CrowMan extends AbstractEnemy {
    public CrowMan(PointF pos) {
        super(pos, GameCharacters.CROW_MAN, 200, 1, 20);
    }

    @Override
    int getDefaultChaseDis() {
        return 5;
    }
}
