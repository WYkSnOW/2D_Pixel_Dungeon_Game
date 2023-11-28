package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotSame;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class DamageBaseOnDifficultyUnitTest {
    @Test
    public void damageBaseOnDifficulty() {
        // damage based on difficulty
        // it accounts for various difficulties
        // there are seperate integers that are created
        PlayingLogic playingLogic = new PlayingLogic();
        int difficultyOne = 1;
        int difficultyTwo = 2;
        int difficultyThree = 3;
        int enemyAtk = 10;
        // I initialized a damageWhenDiffOne variable
        int damageWhenDiffOne = playingLogic.calculateDamage(enemyAtk, difficultyOne);
        // I initialiazed a damageWhenDiffTwo
        int damageWhenDiffTwo = playingLogic.calculateDamage(enemyAtk, difficultyTwo);
        int damageWhenDiffThree = playingLogic.calculateDamage(enemyAtk, difficultyThree);
        // it calculates the damage based on difficulties
        assertNotSame(damageWhenDiffOne, damageWhenDiffTwo);
        assertNotSame(damageWhenDiffOne, damageWhenDiffThree);
        assertNotSame(damageWhenDiffTwo, damageWhenDiffThree);
    }
}
