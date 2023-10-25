package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;

import org.junit.Test;

public class EnemyAbleMoveUnitTest {
    @Test
    public void enemyAbleMoveUnitTest() {
        //Here are the id of block that allow monster to move
        int[] moveAbleBlock = {15, 123, 124, 124, 136, 148, 149, 112, 125, 113, 126, 114, 127, 140};

        for (int i : moveAbleBlock) {
            assertTrue(GameMap.isMoveAbleBlock(i));
        }
    }
}
