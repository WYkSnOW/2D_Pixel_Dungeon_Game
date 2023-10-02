package com.example.myapplication.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.myapplication.gmaestates.Menu;
import com.example.myapplication.gmaestates.Playing;

public class Game {

    private SurfaceHolder holder;
    private Menu menu;
    private Playing playing;
    private GameLoop gameLoop;
    private GameState currentGameState = GameState.PLAYING;

    public Game(SurfaceHolder holder) {
        this.holder = holder;
        gameLoop = new GameLoop(this);
        initGameStates();
    }

    public void update(double delta) {

        switch (currentGameState) {
            case MENU: menu.update(delta);
            case PLAYING: playing.update(delta);
        }
    }

    public void render() {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        //Draw the game
        switch (currentGameState) {
            case MENU:
                menu.render(c);
                break;
            case PLAYING:
                playing.render(c);
                break;
        }

        holder.unlockCanvasAndPost(c);
    }

    private void initGameStates() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public boolean touchEvent(MotionEvent event) {
        switch (currentGameState) {
            case MENU:
                menu.touchEvents(event);
                break;
            case PLAYING:
                playing.touchEvents(event);
                break;
        }

        return true;
    }

    public void startGameLoop() {
        gameLoop.startGameLoop();
    }

    public enum GameState {
        MENU, PLAYING;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }
}