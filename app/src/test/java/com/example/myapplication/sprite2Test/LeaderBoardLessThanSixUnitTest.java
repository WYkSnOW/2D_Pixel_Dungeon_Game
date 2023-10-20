package com.example.myapplication.sprite2Test;

import static org.junit.Assert.assertEquals;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import org.junit.Test;

//author:Vibha Guru
public class LeaderBoardLessThanSixUnitTest {
    @Test
    public void leaderShipBoardLessThanSix() {

        // add 5 scores to leadership board
        Score scoreOne = new Score(5, 1, "vibha", true);
        Score scoreTwo = new Score(6, 2, "vibha", true);
        Score scoreThree = new Score(7, 2, "vibha", true);
        Score scoreFour = new Score(9, 2, "vibha", true);
        Score scoreFive = new Score(13, 2, "vibha", true);

        Leaderboard.getInstance().addPlayerRecord(scoreOne);
        Leaderboard.getInstance().addPlayerRecord(scoreTwo);
        Leaderboard.getInstance().addPlayerRecord(scoreThree);
        Leaderboard.getInstance().addPlayerRecord(scoreFour);
        Leaderboard.getInstance().addPlayerRecord(scoreFive);

        //expect that score 5 is at top of playerRecords (index 0)
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(0).getScore(),
                scoreFive.getScore());
        //expect that score 4 is in index 1
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(1).getScore(),
                scoreFour.getScore());
        //expect that score 3 is in index 2
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(2).getScore(),
                scoreThree.getScore());
        //expect that score 2 is in index 3
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(3).getScore(),
                scoreTwo.getScore());
        //expect that score 1 is in index 4
        assertEquals(Leaderboard.getInstance().getPlayerRecords().get(4).getScore(),
                scoreOne.getScore());
    }
}
