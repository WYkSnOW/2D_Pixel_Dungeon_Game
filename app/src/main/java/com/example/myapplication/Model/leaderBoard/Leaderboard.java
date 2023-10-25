package com.example.myapplication.Model.leaderBoard;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.myapplication.Model.entities.Player.Player;
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
    private Paint blackPaint = new Paint();
    private Paint boldPaint = new Paint();

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

    public void addPlayerRecord(Score score, boolean finishGame) {
        if (finishGame) {
            this.newestScore = score;
            updatePlayerState();
            playerRecords.add(score);
            updateLeaderBoard();
        }

    }

    public void drawLeaderBoard(Canvas c) {
        paint1.setTextSize(35);
        paint1.setColor(Color.WHITE);

        boldPaint.setTextSize(35);
        boldPaint.setColor(Color.BLACK);
        Typeface boldTypeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        boldPaint.setTypeface(boldTypeface);

        blackPaint.setTextSize(20);
        blackPaint.setColor(Color.BLACK);

        if (Player.getInstance().isWinTheGame()) {
            drawYouWin(c);
        } else {
            drawYouLose(c);
        }


        c.drawText(
                "Name: " + newestScore.getPlayerName(),
                1905,
                580,
                boldPaint);
        c.drawText(
                "Score: " + newestScore.getScore(),
                1905,
                620,
                boldPaint);
        c.drawText(newestScore.getDate(),
                1905,
                660,
                boldPaint);

        for (int i = 0; i < playerRecords.size(); i++) {
            if (playerRecords.get(i).getIsNew()) {
                paint1.setColor(Color.WHITE);
                drawRankInfo(c, i);
            } else {
                paint1.setColor(Color.RED);
                drawRankInfo(c, i);
            }
        }
    }
    private void drawYouWin(Canvas c) {
        c.drawText(
                "You Win!",
                1980,
                540,
                boldPaint);
    }
    private void drawYouLose(Canvas c) {
        c.drawText(
                "You Lose",
                1980,
                540,
                boldPaint);
        c.drawText(
                "score wouldn't get recorded",
                1905,
                690,
                blackPaint
        );
    }

    private void drawRankInfo(Canvas c, int i) {
        int addSpace = 105;
        int offSetX = 0;
        if (Integer.toString(playerRecords.get(i).getScore()).length() == 1) {
            offSetX = 10;
        }
        c.drawText(
                playerRecords.get(i).getPlayerName(),
                220,
                410 + (i * addSpace),
                paint1);
        c.drawText(
                "" + playerRecords.get(i).getScore(),
                566 + offSetX,
                419 + (i * addSpace) + 50,
                paint1);
        c.drawText(playerRecords.get(i).getDate(),
                280,
                405 + (i * addSpace) + 20,
                blackPaint);
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