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
        Score s3 = new Score(0, 1, "Nikhil3", true);
        Score s4 = new Score(0, 1, "Nikhil4", true);

        int offsetOne =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s1.getScore()).length());
        int offsetTwo =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s2.getScore()).length());
        int offsetThree =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s3.getScore()).length());
        int offsetFour =
                Leaderboard.getInstance().calScoreOffset(Integer.toString(s4.getScore()).length());

        assertNotEquals(offsetOne, offsetTwo);
        assertNotEquals(offsetThree, offsetFour);
    }
}


