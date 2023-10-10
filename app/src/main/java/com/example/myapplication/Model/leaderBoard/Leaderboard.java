package com.example.myapplication.Model.leaderBoard;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.leaderBoard.Score.ScoreComparator;

import java.util.ArrayList;

public class Leaderboard {
    private static Leaderboard instance;
    private ArrayList<Score> playerRecords;
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();

    private Leaderboard() {
        playerRecords = new ArrayList<>();
        paint1 = new Paint();
        paint1.setTextSize(50);
        paint1.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(50);
        paint2.setColor(Color.RED);
    }

    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public ArrayList<Score> getPlayerRecords() {
        return playerRecords;
    }

    public void addPlayerRecord(Score score) {
        updatePlayerState();
        playerRecords.add(score);
        updateLeaderBoard();
    }

    public void drawLeaderBoard(Canvas c, Score currentScore) {
        c.drawText("Score of this game: " + currentScore.getScore(), 1500, 200, paint2);
        for (int i = 0; i < playerRecords.size(); i++) {
            if (playerRecords.get(i).getIsNew()) {
                c.drawText(
                        "Score: " + playerRecords.get(i).getScore(), 800, 200 + (i * 50), paint2
                );
            } else {
                c.drawText(
                        "Score: " + playerRecords.get(i).getScore(), 800, 200 + (i * 50), paint1
                );
            }
        }
    }

    public void updateLeaderBoard() {
        playerRecords.sort(new ScoreComparator());
        if (playerRecords.size() > 6) {
            playerRecords.subList(6, playerRecords.size()).clear();
        }
    }

    public void updatePlayerState() {
        for (Score p : playerRecords) {
            p.setIsNew(false);
        }
    }

    // 其他需要的方法...
}