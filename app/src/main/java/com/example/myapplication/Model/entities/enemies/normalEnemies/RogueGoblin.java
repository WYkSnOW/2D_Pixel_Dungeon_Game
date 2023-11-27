package com.example.myapplication.Model.entities.enemies.normalEnemies;

import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.RogueGoblinStrategy;

public class RogueGoblin extends AbstractEnemy {
    public RogueGoblin(PointF pos) {
        super(pos, GameCharacters.ROGUE_GOBLIN, 200, 1, 30, new RogueGoblinStrategy());
    }

    @Override
    int getDefaultChaseDis() {
        return 5;
    }
}
