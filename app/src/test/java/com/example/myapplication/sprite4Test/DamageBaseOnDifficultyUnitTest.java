package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotSame;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class DamageBaseOnDifficultyUnitTest {
    @Test
    public void damageBaseOnDifficulty() {
        PlayingLogic playingLogic = new PlayingLogic();
        int difficultyOne = 1;
        int difficultyTwo = 2;
        int difficultyThree = 3;
        int enemyAtk = 10;
        int damageWhenDiffOne = playingLogic.calculateDamage(enemyAtk, difficultyOne);
        int damageWhenDiffTwo = playingLogic.calculateDamage(enemyAtk, difficultyTwo);
        int damageWhenDiffThree = playingLogic.calculateDamage(enemyAtk, difficultyThree);

        assertNotSame(damageWhenDiffOne, damageWhenDiffTwo);
        assertNotSame(damageWhenDiffOne, damageWhenDiffThree);
        assertNotSame(damageWhenDiffTwo, damageWhenDiffThree);
    }
}
