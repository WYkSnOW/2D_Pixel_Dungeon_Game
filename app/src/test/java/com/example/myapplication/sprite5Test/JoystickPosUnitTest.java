package com.example.myapplication.sprite5Test;



import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.ui.playingUI.PlayingUI;

import org.junit.Test;

public class JoystickPosUnitTest {

    @Test
    public void touchJoystickUnitTest() {
        //Joy stick pos is always relate with height of the screen
        int joyStickY = (int) PlayingUI.JOYSTICK_CENTER_POS.y;
        int gameHeight = GameConstants.UiSize.GAME_HEIGHT;
        assertEquals(joyStickY, gameHeight);

    }
}
