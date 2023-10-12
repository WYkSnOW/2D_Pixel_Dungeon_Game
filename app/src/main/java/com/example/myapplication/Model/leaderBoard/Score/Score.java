package com.example.myapplication.Model.leaderBoard.Score;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Score {
    private String playerName;
    private int difficulty;
    private int score;
    private boolean isNew;
    private String date;

    public Score(int score, int difficulty, String playerName, boolean isNew) {
        this.isNew = isNew;
        this.difficulty = difficulty;
        this.playerName = playerName;
        this.score = score;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.date = currentDateTime.format(formatter);
        } else {
            this.date = "";
        }

    }

    public String getDate() {
        return date;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public boolean getIsNew() {
        return isNew;
    }
}
