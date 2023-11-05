package com.example.myapplication.sprite4Test;

import static org.junit.Assert.assertSame;

import com.example.myapplication.Model.leaderBoard.Score.DifficultyComparator;
import com.example.myapplication.Model.leaderBoard.Score.Score;

import java.util.ArrayList;

import org.junit.Test;
public class CorrectCompareDiffUnitTest {
    @Test
    public void correctCompareDiffUnitTest() {
        // There is a call of comparator that can take in an arrayList of
        // score and sort them based on the difficulty of the game

        Score score1 = new Score(10, 2, "player1", true);
        Score score2 = new Score(10, 3, "player2", true);
        ArrayList<Score> scoreList = new ArrayList<>();
        scoreList.add(score1);
        scoreList.add(score2);

        scoreList.sort(new DifficultyComparator());
        // score 2 has higher difficulty and becomes the first record in the list
        assertSame(scoreList.get(0), score2);
    }

}
