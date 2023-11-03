package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class MoveWhileAttackingUnitTest {

    @Test
    public void moveWhileAttackingUnitTest() {
        //when game check momentOfPlayer, it will check if player is attacking first
        //if so, player will not able to move.

        PlayingLogic playingLogic = new PlayingLogic();

        //while attacking, player not able to move
        boolean isAttackingOne = true;
        assertFalse(playingLogic.checkPlayerAbleMoveWithAttacking(isAttackingOne));

        //while not attacking, player is able to move
        boolean isAttackingTwo = false;
        assertTrue(playingLogic.checkPlayerAbleMoveWithAttacking(isAttackingTwo));


    }
}
