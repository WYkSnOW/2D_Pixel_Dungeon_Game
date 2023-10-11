package com.example.myapplication;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;

import org.junit.Test;


public class PlayerNameValidUnitTest {


    @Test
    public void testIsNameValid() {
        ConfigLogic configLogic = new ConfigLogic();
        assertTrue(configLogic.isNameValid("Tony")); // valid name that is able to use
        assertTrue(configLogic.isNameValid("Tony K")); // valid name that contain space
        assertFalse(configLogic.isNameValid("")); // a empty name which is invalid
        assertFalse(configLogic.isNameValid("    ")); // a name only contain space which is invalid
        assertFalse(configLogic.isNameValid(null)); // a null name which is invalid
    }


}