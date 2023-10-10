package com.example.myapplication.Model.leaderBoard.Score;


public class Score {
    private String playerName;
    private int difficulty;
    private int score;
    private boolean isNew;

    public Score(int score, int difficulty, String playerName, boolean isNew) {
        this.isNew = isNew;
        this.difficulty = difficulty;
        this.playerName = playerName;
        this.score = score;
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
