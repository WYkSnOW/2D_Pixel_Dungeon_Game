package com.example.myapplication;



import static org.junit.Assert.assertEquals;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;


public class LeaderboardVerifyPlayerNameUnitTest {

    //Test to check if player name on the leaderboard is the same as the user's input - Nikhil
    @Test
    public void verifyPlayerNameOnPlayerName() {
        //Make 3 different scores with different
        // names and check if the leaderboard displays them corrrectly
        Score score1 = new Score(20, 1, "nikhil", true);
        Score score2 = new Score(15, 1, "batman", true);
        Score score3 = new Score(10, 1, "superman", true);
        Leaderboard.getInstance().addPlayerRecord(score1);
        Leaderboard.getInstance().addPlayerRecord(score2);
        Leaderboard.getInstance().addPlayerRecord(score3);
        assertEquals(Leaderboard.getInstance().getPlayerRecords()
                .get(0).getPlayerName(), score1.getPlayerName());
        assertEquals(Leaderboard.getInstance().getPlayerRecords()
                .get(1).getPlayerName(), score2.getPlayerName());
        assertEquals(Leaderboard.getInstance().getPlayerRecords()
                .get(2).getPlayerName(), score3.getPlayerName());
    }
}
