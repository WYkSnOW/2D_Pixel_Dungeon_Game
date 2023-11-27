package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;

import com.example.myapplication.Model.entities.enemies.enemyStartegy.EnemyStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

import org.junit.Test;

public class DifferentAnimIdxUnitTest {
    @Test
    public void differentAnimIdx() {
        //During different state, length of the animation is different.
        EnemyStrategy enemyStrategy = new OgreStrategy();
        int maxIndex1 = enemyStrategy.getAnimMaxIndex(EnemyStates.ATK);
        int maxIndex2 = enemyStrategy.getAnimMaxIndex(EnemyStates.IDLE);

        assertNotEquals(maxIndex1, maxIndex2);

    }
}
