package com.example.myapplication.Model.entities.enemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;

public class EnemyFactory {
    public static AbstractEnemy createEnemy(GameCharacters characterType, PointF position) {
        switch (characterType) {
        case ZOMBIE:
            return new Zombie(position);
        case CHEST_MOB:
            return new ChestMob(position);
        case CROW_MAN:
            return new CrowMan(position);
        case STEEL_GOLEM:
            return new SteelGolem(position);
        default:
            throw new IllegalArgumentException("Unknown enemy type!");
        }
    }
}
