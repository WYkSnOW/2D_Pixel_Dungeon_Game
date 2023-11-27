package com.example.myapplication.Model.entities.enemies.normalEnemies;


import android.graphics.PointF;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;


public class Ogre extends AbstractEnemy {
    public Ogre(PointF pos) {
        super(pos, GameCharacters.OGRE, 120, 3, 50, new OgreStrategy());
    }


    @Override
    int getDefaultChaseDis() {
        return 4;
    }
}
