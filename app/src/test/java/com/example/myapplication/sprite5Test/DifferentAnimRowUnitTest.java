package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertNotEquals;

import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;


import org.junit.Test;

public class DifferentAnimRowUnitTest {
    @Test
    public void differentAnimRow() {
        EnemyStates states = EnemyStates.IDLE;
        EnemyStates states2 = EnemyStates.WALK;
        EnemyStates states3 = EnemyStates.ATK;

        
        assertNotEquals(states.getAnimRow(), states2.getAnimRow());
        assertNotEquals(states3.getAnimRow(), states2.getAnimRow());
        assertNotEquals(states.getAnimRow(), states3.getAnimRow());



    }
}
