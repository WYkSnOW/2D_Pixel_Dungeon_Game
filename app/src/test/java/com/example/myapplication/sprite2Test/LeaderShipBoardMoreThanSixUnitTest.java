package com.example.myapplication.sprite2Test;

import static org.junit.Assert.assertEquals;

import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;
//author:Vibha Guru
public class LeaderShipBoardMoreThanSixUnitTest {

    @Test
    public void leaderShipBoardMoreThanSix() {

        // add 7 scores to leadership board
        Score scoreOne = new Score(5, 1, "vibha", true);
        Score scoreTwo = new Score(6, 2, "vibha", true);
        Score scoreThree = new Score(7, 2, "vibha", true);
        Score scoreFour = new Score(9, 2, "vibha", true);
        Score scoreFive = new Score(13, 2, "vibha", true);
        Score scoreSix = new Score(19, 2, "vibha", true);
        Score scoreSeven = new Score(20, 2, "vibha", true);

        Leaderboard.getInstance().addPlayerRecord(scoreOne, true);
        Leaderboard.getInstance().addPlayerRecord(scoreTwo, true);
        Leaderboard.getInstance().addPlayerRecord(scoreThree, true);
        Leaderboard.getInstance().addPlayerRecord(scoreFour, true);
        Leaderboard.getInstance().addPlayerRecord(scoreFive, true);
        Leaderboard.getInstance().addPlayerRecord(scoreSix, true);
        Leaderboard.getInstance().addPlayerRecord(scoreSeven, true);

        //expect that score 7 is at top of playerRecords (index 0)
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(0).getScore(),
                scoreSeven.getScore());
        //expect that score 6 is in index 1
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(1).getScore(),
                scoreSix.getScore());
        //expect that score 5 is in index 2
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(2).getScore(),
                scoreFive.getScore());
        //expect that score 4 is in index 3
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(3).getScore(),
                scoreFour.getScore());
        //expect that score 3 is in index 4
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(4).getScore(),
                scoreThree.getScore());
        //expect that score 2 is in index 5
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(5).getScore(),
                scoreTwo.getScore());

    }
}
