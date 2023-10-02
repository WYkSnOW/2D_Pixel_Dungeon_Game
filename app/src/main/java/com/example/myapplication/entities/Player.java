package com.example.myapplication.entities;

import static com.example.myapplication.main.MainActivity.GAME_HEIGHT;
import static com.example.myapplication.main.MainActivity.GAME_WIDTH;
import android.graphics.PointF;

import com.example.myapplication.helper.GameConstants;

public class Player extends Character {
    private int playerWidth;
    private int playerHeight;

    public Player(GameCharacters characterChoice) {
        super(new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2), characterChoice);
    }

    public void update(double delta, boolean movePlayer) {
        //if (movePlayer) {
        updateAnimation();
        //}
    }
    public void setPlayer(double delta, boolean movePlayer) {
        //if (movePlayer) {
        updateAnimation();
        //}
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth * GameConstants.Sprite.SCALE_MULTIPLIER;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight * GameConstants.Sprite.SCALE_MULTIPLIER;
    }


}
