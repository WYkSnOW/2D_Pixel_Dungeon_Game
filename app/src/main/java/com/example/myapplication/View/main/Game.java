package com.example.myapplication.View.main;

import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.myapplication.ViewModel.gmaestates.Config;
import com.example.myapplication.ViewModel.gmaestates.End;
import com.example.myapplication.ViewModel.gmaestates.Menu;
import com.example.myapplication.ViewModel.gmaestates.Playing;

import java.util.Objects;

public class Game { //All update and render get set over here

    private SurfaceHolder holder;
    private Menu menu;
    private Playing playing;
    private Config config;
    private End end;
    private GameLoop gameLoop;
    private GameState currentGameState = GameState.MENU;

    private MediaPlayer mediaPlayer;

    public Game(SurfaceHolder holder) { //keep track to all game state and allow switch between them
        this.holder = holder;
        gameLoop = new GameLoop(this);
        initGameStates();
    }

    public void update(double delta) { //only update one game state at time

        if (Objects.requireNonNull(currentGameState) == GameState.MENU) {
            menu.update(delta);
            playing.update(delta);
            config.update(delta);
            end.update(delta);
        } else if (currentGameState == GameState.PLAYING) {
            playing.update(delta);
            config.update(delta);
            end.update(delta);
        } else if (currentGameState == GameState.CONFIG) {
            config.update(delta);
            end.update(delta);
        } else if (currentGameState == GameState.END) {
            end.update(delta);
        }
    }

    public void render() {
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        //Draw the game
        if (Objects.requireNonNull(currentGameState) == GameState.MENU) {
            menu.render(c);
        } else if (currentGameState == GameState.PLAYING) {
            playing.render(c);
        } else if (currentGameState == GameState.CONFIG) {
            config.render(c);
        } else if (currentGameState == GameState.END) {
            end.render(c);
        }

        holder.unlockCanvasAndPost(c);
    }

    private void initGameStates() {
        menu = new Menu(this);
        playing = new Playing(this);
        config = new Config(this);
        end = new End(this);
    }

    public boolean touchEvent(MotionEvent event) {
        if (Objects.requireNonNull(currentGameState) == GameState.MENU) {
            menu.touchEvents(event);
        } else if (currentGameState == GameState.PLAYING) {
            playing.touchEvents(event);
        } else if (currentGameState == GameState.CONFIG) {
            config.touchEvents(event);
        } else if (currentGameState == GameState.END) {
            end.touchEvents(event);
        }

        return true;
    }

    public void startGameLoop() {
        gameLoop.startGameLoop();
    }

    public enum GameState {
        MENU, PLAYING, CONFIG, END;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    public Playing getPlaying() {
        return playing;
    }

}