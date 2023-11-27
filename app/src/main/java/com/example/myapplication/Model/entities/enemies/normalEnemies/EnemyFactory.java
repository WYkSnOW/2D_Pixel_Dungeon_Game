package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;

public class EnemyFactory {
    public static AbstractEnemy createEnemy(GameCharacters characterType, PointF position) {
        switch (characterType) {
        case OGRE:
            return new Ogre(position);
        case MINOTAUR:
            return new Minotaur(position);
        case ROGUE_GOBLIN:
            return new RogueGoblin(position);
        case STEEL_GOLEM:
            return new SteelGolem(position);
        default:
            throw new IllegalArgumentException("Unknown enemy type!");
        }
    }
}
