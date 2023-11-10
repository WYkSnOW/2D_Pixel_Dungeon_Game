package com.example.myapplication.sprite4Test;
import static org.junit.Assert.assertFalse;

import com.example.myapplication.Model.environments.GameMap;

import org.junit.Test;

public class EnemyAbleMoveUnitTest {

    @Test
    public void enemyAbleMoveUnitTest() {
        // here are the id of block that doesn't allow the monster to move
        int [] notMoveAbleBlock = {59, 60, 150, 0};

        for (int i : notMoveAbleBlock) {
            assertFalse(GameMap.isMoveAbleBlock(i));
        }

    }
}
