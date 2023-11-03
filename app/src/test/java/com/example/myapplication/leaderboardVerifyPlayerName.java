package com.example.myapplication;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class leaderboardVerifyPlayerName {

    @Test
    public void verifyPlayerNameOnPlayerName() {
        Score score1 = new Score(20, 1, "nikhil", true);
        Score score2 = new Score(15, 1, "batman", true);
        Score score3 = new Score(10, 1, "superman", true);
        Leaderboard.getInstance().addPlayerScore(score1);
        Leaderboard.getInstance().addPlayerScore(score2);
        Leaderboard.getInstance().addPlayerScore(score3);
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(0), score1.getPlayerName());
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(1), score2.getPlayerName());
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(2), score3.getPlayerName());
    }
}
