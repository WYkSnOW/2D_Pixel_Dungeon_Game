package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;

public class LeaderboardRankOnDateUnitTest {
    //When player get the same score, same difficulty in different game,
    //leaderboard will rank the records base on Date(older record get higher rank)

    @Test
    public void leaderboardRankOnDate() {

        //all same but scoreTwo is add after scoreOne which have later Date/time
        Score scoreOne = new Score(10, 1, "Diya", true);
        Score scoreTwo = new Score(10, 1, "Diya", true);

        Leaderboard.getInstance().addPlayerRecord(scoreOne, true);
        Leaderboard.getInstance().addPlayerRecord(scoreTwo, true);


        //ScoreTwo become the 1st record because it have higher difficulty
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(0),
                scoreOne);

        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(1),
                scoreTwo);
    }
}
