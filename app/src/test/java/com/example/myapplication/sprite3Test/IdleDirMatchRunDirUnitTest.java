package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.helper.HelpMethods;

import org.junit.Test;

public class IdleDirMatchRunDirUnitTest {
    @Test
    public void idleDirMatchRunDir() {
        //If player move then stop afterward,
        // standing animation face the same direction as last movement direction

        int lastMoveDirOne = GameConstants.DrawDir.RIGHT;
        int standingDirOne = GameConstants.DrawDir.IDLE_RIGHT;
        assertEquals(standingDirOne, HelpMethods.getIdleAnimation(lastMoveDirOne));

        int lastMoveDirTwo = GameConstants.DrawDir.LEFT;
        int standingDirTwo = GameConstants.DrawDir.IDLE_LEFT;
        assertEquals(standingDirTwo, HelpMethods.getIdleAnimation(lastMoveDirTwo));

        assertNotSame(HelpMethods.getIdleAnimation(lastMoveDirOne),
                HelpMethods.getIdleAnimation(lastMoveDirTwo));
    }
}
