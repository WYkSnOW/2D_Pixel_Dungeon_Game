package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class EnemyAbleMoveUnitTest {
    @Test
    public void enemyAbleMoveUnitTest() {
        //Here are the id of block that doesn't allow monster to move
        int[] notMoveAbleBlock = {59, 60, 150, 0};

        for (int i : notMoveAbleBlock) {
            assertFalse(GameMap.isMoveAbleBlock(i));
        }
    }
}
