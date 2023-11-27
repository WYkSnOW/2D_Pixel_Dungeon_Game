package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.enemies.enemyStartegy.EnemyStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

import org.junit.Test;

public class AtkIdxUnitTest {
    @Test
    public void atkIdxUnitTest() {
        //mob only make damage during some idx of it's atk animation
        EnemyStrategy enemyStrategy = new OgreStrategy();
        int idx1 = 4;
        int idx2 = 3;

        assertTrue(enemyStrategy.isMakingDamage(EnemyStates.ATK, idx1));
        assertFalse(enemyStrategy.isMakingDamage(EnemyStates.ATK, idx2));

    }
}
