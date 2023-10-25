package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertTrue;

import android.graphics.RectF;

import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;
import com.example.myapplication.R;

import org.junit.Test;

public class PlayerEnemyCollisionUnitTest {
    @Test
    public void playerEnemyCollisionUnitTest() {
        PlayingLogic playingLogic = new PlayingLogic();
        RectF playerHitBox = new RectF(0, 0, 100, 100);
        RectF enemyHitBoxOne = new RectF(50, 50, 150, 150);
        RectF enemyHitBoxTwo = new RectF(150, 150, 250, 250);

        assertTrue(playingLogic.checkHitBoxCollision(playerHitBox, enemyHitBoxOne));

    }
}
