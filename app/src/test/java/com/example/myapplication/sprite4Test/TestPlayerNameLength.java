package com.example.myapplication.sprite4Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;
import org.junit.Test;

public class TestPlayerNameLength {
    @Test
    public void playerNameLenLimit() {
        String name1 = "vibha";
        String name2 = "abcdefghijklmnopqurs";
        ConfigLogic configLogic = new ConfigLogic();
        assertTrue(configLogic.nameLengthBelowLimit(name1));
        assertFalse(configLogic.nameLengthBelowLimit(name2));
    }
}
