package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotEquals;


import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;


import org.junit.Test;

public class LocalBaseOnScoreLenUnitTest {
    @Test
    public void localBaseOnScoreLen() {
        Score s1 = new Score(20, 1, "Nikhil", true);
        Score s2 = new Score(0, 1, "Nikhil2", true);

        int offsetOne =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s1.getScore()).length());
        int offsetTwo =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s2.getScore()).length());

        assertNotEquals(offsetOne, offsetTwo);
    }
}


