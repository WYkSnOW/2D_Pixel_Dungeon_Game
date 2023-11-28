package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;

import org.junit.Test;

public class DifferentSpeedUnitTest {
    @Test
    public void differentSpeedUnitTest() {
        //tests if player moves faster when running (i.e. has different speeds when
        // animation player state changes) - Nikhil
        PlayerCharStrategy playerCharStrategy = new CharOne();
        float baseSpeedNum = 100;

        assertTrue(playerCharStrategy.getCurrentSpeed(baseSpeedNum, PlayerStates.WALK)
                < playerCharStrategy.getCurrentSpeed(baseSpeedNum, PlayerStates.RUNNING));

    }
}
