package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.EnemyStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStartegy.OgreStrategy;
import com.example.myapplication.Model.entities.enemies.enemyStates.EnemyStates;

import org.junit.Test;

public class DifferentSpeedUnitTest {
    @Test
    public void differentSpeedUnitTest() {
        //tests if player moves faster when running (i.e. has different speeds) - Nikhil
        PlayerCharStrategy playerCharStrategy = new CharOne();
        float baseSpeed = 100;

        assertTrue(playerCharStrategy.getCurrentSpeed(baseSpeed, PlayerStates.WALK)
                < playerCharStrategy.getCurrentSpeed(baseSpeed, PlayerStates.RUNNING));

    }
}
