package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.environments.GameMap;

import org.junit.Test;

public class CheckAbleMoveUnitTest {
    @Test
    public void checkAbleMoveUnitTest() {
        //Here are the id of block that allow player to move
        int[] moveAbleBlock = {15, 123, 124, 124, 136, 148, 149, 112, 125, 113, 126, 114, 127, 140};

        for (int i : moveAbleBlock) {
            assertTrue(GameMap.isMoveAbleBlock(i));
        }
    }



}
