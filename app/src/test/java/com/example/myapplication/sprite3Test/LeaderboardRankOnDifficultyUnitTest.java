package com.example.myapplication.sprite3Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;

public class LeaderboardRankOnDifficultyUnitTest {
    //When player get the same score in different game,
    //leaderboard will rank the records base on difficulty

    @Test
    public void leaderboardRankOnDifficultyUnitTest() {

        //all same except difficulty
        Score scoreOne = new Score(10, 1, "Diya", true);
        Score scoreTwo = new Score(10, 2, "Diya", true);

        Leaderboard.getInstance().addPlayerRecord(scoreOne, true);
        Leaderboard.getInstance().addPlayerRecord(scoreTwo, true);


        //ScoreTwo become the 1st record because it have higher difficulty
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(0).getDifficulty(),
                scoreTwo.getDifficulty());

        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(1).getDifficulty(),
                scoreOne.getDifficulty());
    }
}
