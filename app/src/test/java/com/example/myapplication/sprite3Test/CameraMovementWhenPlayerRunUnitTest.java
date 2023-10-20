package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;


import android.graphics.PointF;

import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class CameraMovementWhenPlayerRunUnitTest {
    @Test
    public void cameraMovementWhenPlayerRun() {
        //When character move to the right, camera should move to the left

        float xSpeed = 1;
        float ySpeed = 1;
        float baseSpeed = 300;
        PointF moveVector = HelpMethods.playerMovementRun(xSpeed, ySpeed, baseSpeed);

        int xVector = (int) moveVector.x;
        int yVector = (int) moveVector.y;

        assertTrue(xVector <= 0);
        assertTrue(yVector <= 0);
    }

}
