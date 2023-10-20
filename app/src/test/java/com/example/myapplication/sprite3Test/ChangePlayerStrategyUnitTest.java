package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertNotSame;


import android.graphics.PointF;

import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerIdle;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerMoveStrategy;
import com.example.myapplication.Model.helper.playerMoveStartegy.PlayerRun;

import org.junit.Test;

public class ChangePlayerStrategyUnitTest {
    @Test
    public void changePlayerStrategyUnitTest() {
        float xSpeed = 1;
        float ySpeed = 2;
        float baseSpeed = 100;

        PlayerMoveStrategy playerIdle = new PlayerIdle();
        PlayerMoveStrategy playerRun = new PlayerRun();

        PointF rStrategy1 = playerIdle.playerMovement(xSpeed, ySpeed, baseSpeed);
        PointF rStrategy2 = playerRun.playerMovement(xSpeed, ySpeed, baseSpeed);

        //different strategy return different result when calling same method
        assertNotSame(rStrategy1, rStrategy2);


    }
}
