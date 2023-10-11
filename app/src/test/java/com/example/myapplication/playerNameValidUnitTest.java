package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import org.junit.Test;

public class playerNameValidUnitTest {

    @Test
    public void testIsNameValid() {
        ConfigLogic configLogic = new ConfigLogic();
        assertTrue(configLogic.isNameValid("Tony"));
        // valid name that is able to use
        assertTrue(configLogic.isNameValid("Tony K"));
        // valid name that contain space but is able to use
        assertFalse(configLogic.isNameValid(""));
        // a empty name which is invalid
        assertFalse(configLogic.isNameValid("    "));
        // a name only contain space which is invalid
        assertFalse(configLogic.isNameValid(null));
        // a null name which is invalid
    }

    @Test
    public void testIsNameValid2 () {

        Score score = new Score(10,1,"tony",true);
        Leaderboard.getInstance().addPlayerRecord(score);
        assertEquals(Leaderboard.getInstance()
                .getPlayerRecords()
                .get(0).getDifficulty(),
                1);
    }


}