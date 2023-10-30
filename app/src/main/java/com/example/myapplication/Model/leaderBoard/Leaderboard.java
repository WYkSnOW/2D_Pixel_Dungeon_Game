package com.example.myapplication.Model.leaderBoard;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.leaderBoard.Score.DifficultyComparator;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.leaderBoard.Score.ScoreComparator;
import com.example.myapplication.Model.ui.GameImages;

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
    private void drawPlayer(Canvas c) {
        Player.getInstance().setCurrentStates(PlayerStates.WALK);
        c.drawBitmap(Player.getInstance().getGameCharType()
                        .getSprite(2, Player.getInstance().getAniIndex()),
                1880 + 50,
                390 + 300 - Player.getInstance().getHitBoxOffSetY() / 2f,
                null);
        Player.getInstance().updatePlayerAnim();
    }

    private void drawLeaderBoardUi(Canvas c) {
        Bitmap resultBar = GameImages.PLAYER_LOSE_BAR.getImage();
        if (Player.getInstance().isWinTheGame()) {
            resultBar = GameImages.PLAYER_WIN_BAR.getImage();
        }

        c.drawBitmap(
                resultBar,
                (int) ((GAME_WIDTH / 2)
                        - (GameImages.PLAYER_WIN_BAR.getWidth() / 2) + 40),
                30,
                null
        );
        c.drawBitmap(
                GameImages.LEADERBOARD.getImage(),
                100,
                180,
                null
        );
        c.drawBitmap(
                GameImages.CURRENT_BOARD.getImage(),
                GAME_WIDTH - 340,
                390,
                null
        );
    }

    public void drawLeaderBoard(Canvas c) {

        drawLeaderBoardUi(c);

        if (Player.getInstance().isWinTheGame()) {
            drawYouWin(c);
        } else {
            drawYouLose(c);
        }
        drawText(c);
        drawPlayer(c);

    }

    private void drawText(Canvas c) {
        paint1.setTextSize(35);
        paint1.setColor(Color.WHITE);

        boldPaint.setTextSize(35);
        boldPaint.setColor(Color.BLACK);
        Typeface boldTypeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        boldPaint.setTypeface(boldTypeface);

        blackPaint.setTextSize(20);
        blackPaint.setColor(Color.BLACK);
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
                GameConstants.UiSize.GAME_WIDTH - 240,
                390 + 150,
                boldPaint);
    }
    private void drawYouLose(Canvas c) {
        c.drawText(
                "You Lose",
                GameConstants.UiSize.GAME_WIDTH - 240,
                390 + 150,
                boldPaint);
        c.drawText(
                "score wouldn't get recorded",
                GameConstants.UiSize.GAME_WIDTH - 315,
                390 + 300,
                blackPaint
        );
    }

    private void drawRankInfo(Canvas c, int i) {
        int addSpace = 105;
        c.drawText(
                playerRecords.get(i).getPlayerName(),
                220,
                410 + (i * addSpace),
                paint1);
        c.drawText(
                "" + playerRecords.get(i).getScore(),
                566 + calScoreOffset(Integer.toString(playerRecords.get(i).getScore()).length()),
                419 + (i * addSpace) + 50,
                paint1);
        c.drawText(playerRecords.get(i).getDate(),
                280,
                405 + (i * addSpace) + 20,
                blackPaint);
    }

    public int calScoreOffset(int length) {
        if (length == 1) {
            return 10;
        }
        return 0;
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