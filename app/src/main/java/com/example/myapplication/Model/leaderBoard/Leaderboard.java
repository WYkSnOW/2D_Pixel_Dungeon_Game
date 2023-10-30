package com.example.myapplication.Model.leaderBoard;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
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
    private final PointF recordBoardPos = new PointF(100, 180);
    private final PointF resultBarPos = new PointF(
            (int) ((GAME_WIDTH / 2.0) - (GameImages.PLAYER_WIN_BAR.getWidth() / 2.0) + 40.0),
            30
    );
    private final PointF currentBoardPos = new PointF(
            GAME_WIDTH - GameImages.CURRENT_BOARD.getWidth(),
            390
    );

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
                        .getSprite(PlayerStates.WALK.getAnimRow(), Player.getInstance().getAniIndex()),
                currentBoardPos.x,
                currentBoardPos.y + GameImages.CURRENT_BOARD.getHeight() - Player.getInstance().getCharacterHeight() - 20,
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
                resultBarPos.x,
                resultBarPos.y,
                null
        );
        c.drawBitmap(
                GameImages.LEADERBOARD.getImage(),
                recordBoardPos.x,
                recordBoardPos.y,
                null
        );
        c.drawBitmap(
                GameImages.CURRENT_BOARD.getImage(),
                currentBoardPos.x,
                currentBoardPos.y,
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
                currentBoardPos.x + 20,
                currentBoardPos.y + 200,
                boldPaint);
        c.drawText(
                "Score: " + newestScore.getScore(),
                currentBoardPos.x + 20,
                currentBoardPos.y + 240,
                boldPaint);
        c.drawText(newestScore.getDate(),
                currentBoardPos.x + 20,
                currentBoardPos.y + 280,
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
                currentBoardPos.x + (int) (GameImages.CURRENT_BOARD.getWidth() / 4.0) + 20,
                currentBoardPos.y + 150,
                boldPaint);
    }
    private void drawYouLose(Canvas c) {
        c.drawText(
                "You Lose",
                currentBoardPos.x + (int) (GameImages.CURRENT_BOARD.getWidth() / 4.0) + 20,
                currentBoardPos.y + 150,
                boldPaint);
        c.drawText(
                "score wouldn't get recorded",
                currentBoardPos.x + 40,
                currentBoardPos.y + 300,
                blackPaint
        );
    }

    private void drawRankInfo(Canvas c, int i) {
        int addSpace = 105;
        c.drawText(
                playerRecords.get(i).getPlayerName(),
                recordBoardPos.x + 120,
                recordBoardPos.y + 230 + (i * addSpace),
                paint1);
        c.drawText(
                "" + playerRecords.get(i).getScore(),
                recordBoardPos.x + 466 + calScoreOffset(Integer.toString(playerRecords.get(i).getScore()).length()),
                recordBoardPos.y + 239 + (i * addSpace) + 50,
                paint1);
        c.drawText(playerRecords.get(i).getDate(),
                recordBoardPos.x + 180,
                recordBoardPos.y + 225 + (i * addSpace) + 20,
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