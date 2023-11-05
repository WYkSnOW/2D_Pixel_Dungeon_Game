package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class ChangeBackgroundSizeBaseOnScreenUnitTest {
    @Test
    public void changeBackgroundSizeBaseOnScreen() {
        //gameWidth and gameHeight represent the size of physical cell phone
        //Game will calculate ratio and rescale the size of background to fix the screen
        double gameWidth = 1080;
        double gameHeight = 2200;

        double backgroundXOne = 1080;
        double backgroundYOne = 1980;
        double ratioOne = HelpMethods.getScaleRatio(
                backgroundXOne,
                backgroundYOne,
                gameWidth,
                gameHeight
        );
        //after rescale, size of background will be >= game size.
        assertTrue(backgroundXOne * ratioOne >= gameWidth);
        assertTrue(backgroundYOne * ratioOne >= gameHeight);

        double backgroundXTwo = 370;
        double backgroundYTwo = 330;
        double ratioTwo = HelpMethods.getScaleRatio(
                backgroundXTwo,
                backgroundYTwo,
                gameWidth,
                gameHeight
        );
        //when size the background is different, they get different rescale ratio
        assertNotSame(ratioOne, ratioTwo);
    }
}
