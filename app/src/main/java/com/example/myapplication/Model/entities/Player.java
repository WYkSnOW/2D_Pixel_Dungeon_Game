package com.example.myapplication.Model.entities;

import static com.example.myapplication.View.main.MainActivity.gameHeight;
import static com.example.myapplication.View.main.MainActivity.gameWidth;
import android.graphics.PointF;

public class Player extends Character {

    private long lastUpdate;
    private int gameTime;
    private String playerName;
    private int difficulty;
    private int currentHealth;


    public Player(GameCharacters characterChoice) {
        super(new PointF(gameWidth / 2, gameHeight / 2), characterChoice);
        initializeGameTime();
    }

    public void update(double delta) {
        //if (movePlayer) {
        updateAnimation();
        //}
        updateGameTime();
    }


    public void setPlayer(double delta, boolean movePlayer) {
        //if (movePlayer) {
        updateAnimation();
        //}
    }

    private void updateGameTime() {
        long now = System.currentTimeMillis();
        if (now - lastUpdate >= 1000) { //increase gameTime every 100 milli second(1 second)
            gameTime++;
            System.out.println("" + gameTime);
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
}
