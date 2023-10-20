package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.graphics.PointF;

import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class CameraMovementWhenPlayerLdleUnitTest {
    @Test
    public void cameraMovementWhenPlayerIdle() {
        //When character is in idle condition, no movement will happen when function get call.


        PointF moveVector = HelpMethods.playerMovementIdle();

        int xVector = (int) moveVector.x;
        int yVector = (int) moveVector.y;

        assertEquals(0, xVector);
        assertEquals(0, yVector);
    }
}
