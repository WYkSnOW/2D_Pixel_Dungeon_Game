package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;

public class LocalBaseOnScoreLenUnitTest {
    @Test
    public void localBaseOnScoreLen() {
        //score might have different length(like 0 have length 1 while 20 have length 2)
        //the x position of score show on leaderboard will different base on it's length

        Score score1 = new Score(20, 1, "Tony", true);
        Score score2 = new Score(0, 1, "Tony", true);

        int offsetOne = Leaderboard.getInstance().calScoreOffset(Integer.toString(score1.getScore()).length());
        int offsetTwo = Leaderboard.getInstance().calScoreOffset(Integer.toString(score2.getScore()).length());

        assertNotEquals(offsetOne, offsetTwo);

    }
}
