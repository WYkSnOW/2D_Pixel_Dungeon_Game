package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;

import org.junit.Test;

public class DifferentSpeedUnitTest {
    @Test
    public void differentSpeedUnitTest() {
        //player move faster when running.
        PlayerCharStrategy playerCharStrategy = new CharOne();
        float baseSpeed = 100;

        assertTrue(playerCharStrategy.getCurrentSpeed(baseSpeed, PlayerStates.WALK)
                < playerCharStrategy.getCurrentSpeed(baseSpeed, PlayerStates.RUNNING));

    }
}
