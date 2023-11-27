package com.example.myapplication.sprite5Test;


import static org.junit.Assert.assertNotEquals;
import com.example.myapplication.Model.helper.GameConstants;


import org.junit.Test;


public class DifferentFaceDirUnitTest {
    @Test
    public void differentFaceDirUnitTest() {
        //different number represent dir


        int right = GameConstants.MoveDir.RIGHT;
        int left = GameConstants.MoveDir.LEFT;


        assertNotEquals(right, left);


    }
}
