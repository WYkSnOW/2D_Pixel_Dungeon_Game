package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;


import org.junit.Test;

public class TestPlayerNameLenLimit {
    @Test
    public void testPlayerNameLen() {
        String name1 = "Vibha";
        String name2 = "askjfhagwkjrthkkh" //name more than 15 characters

        ConfigLogic configLogic = new ConfigLogic();

        assertTrue(configLogic.nameLengthBelowLimit(name1));
        assertFalse(configLogic.nameLengthBelowLimit(name2));
    }
}


