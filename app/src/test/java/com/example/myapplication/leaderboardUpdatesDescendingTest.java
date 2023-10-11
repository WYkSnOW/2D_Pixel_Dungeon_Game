package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;

import java.util.ArrayList;

public class leaderboardUpdatesDescendingTest {
    @Test
    public void leaderboardUpdatesDescendingTest() {

        // I have created sample leaderboard scores (author: Diya)
        Score score1 = new Score(100, 2, "Player1", true);
        Score score2 = new Score(90, 1, "Player2", true);
        Score score3 = new Score(150, 3, "Player3", true);


        // I have added these to the leaderboard here
        Leaderboard.getInstance().addPlayerRecord(score1);
        Leaderboard.getInstance().addPlayerRecord(score2);
        Leaderboard.getInstance().addPlayerRecord(score3);

        // This for-loop checks if the previous number is greater or equal to the one after it.
        // This helps to ensure it is in decending order
        for (int i = 1; i < Leaderboard.getInstance().getPlayerRecords().size(); i++) {
            assertTrue(Leaderboard.getInstance().getPlayerRecords().get(i - 1).getScore() >= Leaderboard.getInstance().getPlayerRecords().get(i).getScore());
        }

    }


}