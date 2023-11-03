package com.example.myapplication.Model.entities.enemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;

public class ChestMob extends AbstractEnemy {
    public ChestMob(PointF pos) {
        super(pos, GameCharacters.CHEST_MOB, 100, 3);
    }


}
