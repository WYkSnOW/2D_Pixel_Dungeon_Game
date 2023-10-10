package com.example.myapplication.Model.entities.Player;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.leaderBoard.Score.Score;

public class Player extends Character {

    private long lastUpdate;
    private int gameTime;
    private String playerName;
    private int difficulty;
    private int startingHealth;
    private int currentHealth;
    private int currentScore;
    private static Player instance;

    public Player(GameCharacters characterChoice) {
        super(new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2), characterChoice);
        initializeGameTime();
    }

    private void resetPlayer() {
        initializeGameTime();
    }
    public synchronized void setCharacterChoice(GameCharacters characterChoice) {
        this.gameCharType = characterChoice;

        PointF pos = new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2);

        float width = gameCharType.getCharacterWidth() - gameCharType.getHitBoxOffSetX();
        float height = gameCharType.getCharacterHeight() - gameCharType.getHitBoxOffSetY();
        this.hitBox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);

        this.characterWidth = gameCharType.getCharacterWidth();
        this.characterHeight = gameCharType.getCharacterHeight();
        this.hitBoxOffsetX = gameCharType.getHitBoxOffSetX();
        this.hitBoxOffSetY = gameCharType.getHitBoxOffSetY();

        resetPlayer();
    }

    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player(GameCharacters.WARRIOR);
        }
        return instance;
    }


    public void update(double delta) {
        //if (movePlayer) {
        updateAnimation();
        //}
        updateGameTime();
        updateGameScore();
    }


    public int getCurrentScore() {
        return currentScore;
    }

    private void updateGameScore() {
        int tempScore = 20 - gameTime;
        if (tempScore >= 0) {
            this.currentScore = tempScore;
        }
    }

    private void updateGameTime() {
        long now = System.currentTimeMillis();
        if (now - lastUpdate >= 1000) { //increase gameTime every 100 milli second(1 second)
            gameTime++;
            //System.out.println("" + gameTime);
            //System.out.println("" + currentScore);
            lastUpdate += 1000;
        }
    }

    public void initializeGameTime() {
        gameTime = 0;
        lastUpdate = System.currentTimeMillis();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        this.startingHealth = calculateStartingHealth(difficulty);
        this.currentHealth = startingHealth;
    }

    public static int calculateStartingHealth(int difficulty) {
        if (difficulty > 0) {
            return (int) (100.0 / difficulty);
        } else {
            return  100;
        }
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDifficulty() {
        return difficulty;

    }
    public int getCurrentHealth() {
        return currentHealth;
    }

    public Score sumbitScore() {
        return new Score(currentScore, difficulty, playerName, true);
    }

}
