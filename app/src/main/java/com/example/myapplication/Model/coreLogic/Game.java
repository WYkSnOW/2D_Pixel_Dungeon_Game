package com.example.myapplication.Model.coreLogic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.View.main.gameStates.Config;

import com.example.myapplication.View.main.gameStates.End;
import com.example.myapplication.View.main.gameStates.Menu;
import com.example.myapplication.View.main.gameStates.Playing;
import com.example.myapplication.ViewModel.MainViewModel;

import java.util.Objects;

public class Game { //All update and render get set over here

    private SurfaceHolder holder;
    private Menu menu;
    private Playing playing;
    private Config config;
    private End end;
    private GameLoop gameLoop;
    private GameState currentGameState = GameState.MENU;


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
        menu = new Menu(this, MainViewModel.getGameContext());
        playing = new Playing(this, MainViewModel.getGameContext());
        config = new Config(this, MainViewModel.getGameContext());
        end = new End(this, MainViewModel.getGameContext());
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
        MENU, PLAYING, CONFIG, END, RESTART;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }
    public void setGameStateToEnd() {
        //Leaderboard.getInstance().addPlayerRecord(player.sumbitScore());
        end.addPlayerRecord(Player.getInstance().sumbitScore());
        setCurrentGameState(Game.GameState.END);
    }

    public Playing getPlaying() {
        return playing;
    }
    public End getEnd() {
        return end;
    }
    public Config getConfig() {
        return config;
    }
    public void restartGame() {
        config.initConfig();
        playing.initPlaying();
    }

}