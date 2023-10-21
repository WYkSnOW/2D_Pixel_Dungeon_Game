package com.example.myapplication.Model.leaderBoard;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.myapplication.Model.leaderBoard.Score.DifficultyComparator;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.leaderBoard.Score.ScoreComparator;
import java.util.ArrayList;

public class Leaderboard {
    private static Leaderboard instance;
    private ArrayList<Score> playerRecords;
    private Score newestScore;
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Paint timePaint = new Paint();

    private Leaderboard() {
        playerRecords = new ArrayList<>();
        this.newestScore = new Score(0, 0, "player", true);
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
        this.newestScore = score;
        updatePlayerState();
        playerRecords.add(score);
        updateLeaderBoard();
    }

    public void drawLeaderBoard(Canvas c) {
        paint1.setTextSize(35);
        paint1.setColor(Color.WHITE);

        paint2.setTextSize(35);
        paint2.setColor(Color.RED);

        timePaint.setTextSize(20);
        timePaint.setColor(Color.BLACK);

        c.drawText(
                "Name: " + newestScore.getPlayerName(),
                1910,
                540,
                paint2);
        c.drawText(
                "Score: " + newestScore.getScore(),
                1910,
                580,
                paint2);
        c.drawText(newestScore.getDate(),
                1910,
                620,
                paint2);

        for (int i = 0; i < playerRecords.size(); i++) {
            if (playerRecords.get(i).getIsNew()) {
                drawRankInfo(c, i, paint2, timePaint);
            } else {
                drawRankInfo(c, i, paint1, timePaint);
            }
        }
    }

    private void drawRankInfo(Canvas c, int i, Paint textpaint, Paint timePaint) {
        int addSpace = 105;
        c.drawText(
                playerRecords.get(i).getPlayerName(),
                220,
                410 + (i * addSpace),
                textpaint);
        c.drawText(
                "" + playerRecords.get(i).getScore(),
                565,
                420 + (i * addSpace) + 50,
                textpaint);
        c.drawText(playerRecords.get(i).getDate(),
                280,
                405 + (i * addSpace) + 20,
                timePaint);
    }

    public void updateLeaderBoard() {
        playerRecords.sort(new DifficultyComparator());
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