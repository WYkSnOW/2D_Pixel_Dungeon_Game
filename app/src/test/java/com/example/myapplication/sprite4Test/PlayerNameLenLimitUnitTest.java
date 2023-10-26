package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;

import org.junit.Test;

public class PlayerNameLenLimitUnitTest {
    @Test
    public void playerNameLenLimit() {
        //user can not input a name that is more than 15 characters

        String name1 = "Tony"; //length 4 which is valid
        String name2 = "ForSureThisIsMoreThan15Characters"; // a invalid name

        ConfigLogic configLogic = new ConfigLogic();

        assertTrue(configLogic.nameLengthBelowLimit(name1));
        assertFalse(configLogic.nameLengthBelowLimit(name2));
    }
}
