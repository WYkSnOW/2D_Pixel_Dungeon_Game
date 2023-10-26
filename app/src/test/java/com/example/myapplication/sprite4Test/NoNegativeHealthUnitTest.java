package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class NoNegativeHealthUnitTest {
    @Test
    public void enemyAbleMoveUnitTest() {
        //the min health that player can have is 0, it will not goes to negative
        int currentHealth = 1;
        int enemyAtk = 5;
        int difficulty = 1;
        PlayingLogic playingLogic = new PlayingLogic();

        int newHealth = playingLogic.calculateNewHealthForPlayer(
                currentHealth, enemyAtk, difficulty);

        assertEquals(0, newHealth);


    }

}
