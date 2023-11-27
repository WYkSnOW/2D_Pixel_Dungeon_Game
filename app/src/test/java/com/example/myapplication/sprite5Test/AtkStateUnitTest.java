package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.enemies.enemyStartegy.EnemyStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

import org.junit.Test;

public class AtkStateUnitTest {
    @Test
    public void atkIdxUnitTest() {
        //mob only make damage during Attack state
        EnemyStrategy enemyStrategy = new OgreStrategy();
        int idx = 4;

        assertTrue(enemyStrategy.isMakingDamage(EnemyStates.ATK, idx));
        assertFalse(enemyStrategy.isMakingDamage(EnemyStates.IDLE, idx));
        assertFalse(enemyStrategy.isMakingDamage(EnemyStates.WALK, idx));
        assertFalse(enemyStrategy.isMakingDamage(EnemyStates.DEATH, idx));

    }
}
