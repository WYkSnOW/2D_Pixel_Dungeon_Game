package com.example.myapplication.sprite4Test;


import com.example.myapplication.Model.leaderBoard.Leaderboard;
import org.junit.Test;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import static org.junit.Assert.assertEquals;


public class NoRecordWhenLoseUnitTest {
    @Test
    public void noRecordWhenLose() {
        Score score = new Score(20, 3, "Vibha", true);
        Leaderboard.getInstance().addPlayerRecord(score, false);


        assertEquals(0, Leaderboard.getInstance().getPlayerRecords().size());
    }
}

