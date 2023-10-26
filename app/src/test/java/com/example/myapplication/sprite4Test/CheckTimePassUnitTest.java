package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class CheckTimePassUnitTest {
    @Test
    public void checkTimePassUnitTest() {
        int timeRange = 1; // 1 second

        //1000 millisecond = 1 second
        long lastTime1 = System.currentTimeMillis() - 50; //0.5 second pass after lastTime
        assertFalse(HelpMethods.checkTimePass(lastTime1, timeRange));

        long lastTime2 = System.currentTimeMillis() - 2000; //2 second pass after lastTime
        assertTrue(HelpMethods.checkTimePass(lastTime2, timeRange));
    }
}
