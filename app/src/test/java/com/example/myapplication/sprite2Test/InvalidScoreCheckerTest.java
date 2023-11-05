package com.example.myapplication.sprite2Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.entities.Player.Player;

import org.junit.Test;

// Authored by Diya Jain
public class InvalidScoreCheckerTest {

    @Test
    public void testScoreIsAlwaysAboveZero() {

        // Testing with a negative value should expect 0
        int score1 = -12;
        assertEquals(0, Player.checkScoreAboveZero(score1));

        // Testing with a positive value should expect the value
        int score2 = 12;
        assertEquals(score2, Player.checkScoreAboveZero(score2));

    }
}
