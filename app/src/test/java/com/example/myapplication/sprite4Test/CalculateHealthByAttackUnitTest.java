package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class CalculateHealthByAttackUnitTest {
    @Test
    public void calculateHealthByAttackUnitTest() {
        //Calculate health base on given attack power and defence
        PlayingLogic playingLogic = new PlayingLogic();
        int currentHealth = 100;
        int enemyAttack = 10;
        int playerDefense = 5;

        int newHealth
                = playingLogic.calculateHealthByAtk(currentHealth, enemyAttack, playerDefense);
        assertEquals(95, newHealth);

    }
}
