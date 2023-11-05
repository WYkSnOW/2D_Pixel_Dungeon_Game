package com.example.myapplication.sprite4Test;


import static org.junit.Assert.assertTrue;

import android.graphics.PointF;

import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class RandomPosUnitTest {
    @Test
    public void randomPosUnitTest() {
        //when creating a new enemy, we need a random location in map
        //and we can use a method to generate a PointF position

        PointF pos1 = HelpMethods.generateRandomPos(10, 10);
        assertTrue(pos1.x < 10); //this position will locate inside of the map
        assertTrue(pos1.y < 10);


        int differentCount = 0;
        int sameCount = 0;
        for (int i = 0; i < 1000; i++) {
            PointF pos2 = HelpMethods.generateRandomPos(300, 300);
            PointF pos3 = HelpMethods.generateRandomPos(300, 300);
            if (pos2 == pos3) {
                sameCount++;
            } else {
                differentCount += 1;
            }
        }
        assertTrue(differentCount > sameCount);
        //pos2 and pos3 have same range, but since we are getting random
        // location within the range, they should be different for most of time
    }
}
