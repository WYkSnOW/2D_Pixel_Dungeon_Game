package com.example.myapplication.Model.leaderBoard.Score;

import java.util.Comparator;

public class DifficultyComparator implements Comparator<Score> {

    @Override
    public int compare(Score p1, Score  p2) {
        return Integer.compare(p2.getDifficulty(), p1.getDifficulty());
    }


}