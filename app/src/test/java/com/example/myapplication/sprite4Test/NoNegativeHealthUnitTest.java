package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;
public class NoNegativeHealthUnitTest {

    @Test
    public void enemyIsAbleToMoveUnitTest() {
        //Nikhil: the minimum health the player can have 0, health cannot be < 0
        int currHealth = 1;
        int enemyAttack  = 5;
        int difficulty = 1;
        int playerDefence = 0;
        PlayingLogic playingLogic = new PlayingLogic();

        int newHealth
                = playingLogic.calculateNewHealthForPlayer(currHealth, enemyAttack, difficulty, 0);

        assertEquals(0, newHealth);
    }
}
