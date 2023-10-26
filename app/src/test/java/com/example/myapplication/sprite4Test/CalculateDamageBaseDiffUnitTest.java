package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotEquals;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class CalculateDamageBaseDiffUnitTest {
    @Test
    public void calculateDamageBaseDiffUnitTest() {
        //when player choice different difficulty, enemy does different amount of damage to player
        PlayingLogic playingLogic = new PlayingLogic();
        int diffOne = 1;
        int diffTwo = 2;
        int diffThree = 3;
        int enemyAtk = 5;

        int damageOne = playingLogic.calculateDamage(enemyAtk, diffOne);
        int damageTwo = playingLogic.calculateDamage(enemyAtk, diffTwo);
        int damageThree = playingLogic.calculateDamage(enemyAtk, diffThree);

        assertNotEquals(damageOne, damageTwo);
        assertNotEquals(damageOne, damageThree);
        assertNotEquals(damageThree, damageTwo);
    }

}
