package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import org.junit.Test;

public class NoRecordWhenLose {

    @Test
    public void playerEnemyCollisionUnitTest() {
        //if player lose the game, leaderboard will not get update
        Score score = new Score(20, 3, "Tony", true);
        Leaderboard.getInstance().addPlayerRecord(score, false); //Player lose the game

        //No score has been added to the leaderboard
        assertEquals(0, Leaderboard.getInstance().getPlayerRecords().size());
    }

}
