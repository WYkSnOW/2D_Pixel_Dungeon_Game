package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class CalculateHealthByAtkUnitTest {
    @Test
    public void calculateHealthByAtkUnitTest() {
        //calculate health base on given attack power
        PlayingLogic playingLogic = new PlayingLogic();
        int currentHealth = 100;
        int enemyAtk = 10;

        int newHealth = playingLogic.calculateHealthByAtk(currentHealth, enemyAtk);
        assertEquals(90, newHealth);
    }

}
