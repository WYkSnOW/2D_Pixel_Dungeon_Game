package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;

import org.junit.Test;

public class ConfigUnitTest {

    @Test
    public void difficultySettingSelectUnitTest() {
        ConfigLogic configLogic = new ConfigLogic();

        //When difficulty selector is on highest difficulty
        int difficultyChoice = 3;
        int difficultyChoiceAfter3 = configLogic.loopDifficultyChoice(difficultyChoice);
        assertEquals("Successfully got difficulty choice 1", difficultyChoiceAfter3, 1);
        assertNotEquals(difficultyChoiceAfter3, 4);
    }

    @Test
    public void playerCanStartGameUnitTest() {
        ConfigLogic configLogic = new ConfigLogic();
        //When not settings have been selected
        int noCharacterChoice = 0;
        int noDifficultyChoice = 0;
        boolean notValidName = false;

        int characterChoice = 1;
        int difficultyChoice = 1;
        boolean validName = true;

        //When none of the settings had been selected
        assertFalse(configLogic.ableStart(noCharacterChoice, noDifficultyChoice, notValidName));

        //When only one of each setting has been selected
        assertFalse(configLogic.ableStart(characterChoice, noDifficultyChoice, notValidName));
        assertFalse(configLogic.ableStart(noCharacterChoice, difficultyChoice, notValidName));
        assertFalse(configLogic.ableStart(noCharacterChoice, noDifficultyChoice, validName));

        //When only one of each setting has not been selected
        assertFalse(configLogic.ableStart(characterChoice, difficultyChoice, notValidName));
        assertFalse(configLogic.ableStart(characterChoice, noDifficultyChoice, validName));
        assertFalse(configLogic.ableStart(noCharacterChoice, difficultyChoice, validName));

        //When all settings have been selected
        assertTrue(configLogic.ableStart(characterChoice, difficultyChoice, validName));

    }
}