package com.example.myapplication.sprite5Test;

import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.ui.playingUI.PlayingUI;

import org.junit.Test;

public class TouchJoystickUnitTest {

    @Test
    public void touchJoystickUnitTest() {
        //left of joystick return negative, otherwise positive
        //number return will not larger than the radius of joystick.
        assertTrue(PlayingUI.returnDiff(-1) < 0);
        assertTrue(PlayingUI.returnDiff(1) > 0);
    }
}
