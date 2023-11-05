package com.example.myapplication.sprite2Test;
import static org.junit.Assert.assertEquals;


import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;


import org.junit.Test;


public class LeaderboardIsNewUnitTest {

    // Test to ensure that only one leaderboard is created - Nikhil
    @Test
    public void isLeaderboardNew() {
        Score score1 = new Score(10, 1, "nikhil", true);
        Score score2 = new Score(15, 1, "nikhil", true);
        Score score3 = new Score(20, 1, "nikhil", true);
        Leaderboard.getInstance().addPlayerRecord(score1, true);
        Leaderboard.getInstance().addPlayerRecord(score2, true);
        Leaderboard.getInstance().addPlayerRecord(score3, true);
        int count = 0;
        for (int i = 0; i < Leaderboard.getInstance().getPlayerRecords().size(); i++) {
            if (Leaderboard.getInstance().getPlayerRecords().get(i).getIsNew()) {
                count++;
            }
        }
        //count is expected to only be incremented once
        assertEquals(count, 1);
    }
}
