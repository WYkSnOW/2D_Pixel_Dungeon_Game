package com.example.myapplication.ViewModel.gameStatesVideoModel;


import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;
import com.example.myapplication.Model.ui.playingUI.PauseUI;
import com.example.myapplication.Model.ui.playingUI.PlayingUI;
import com.example.myapplication.Model.ui.playingUI.bookUI.BookUI;

public class PlayingViewModel extends ViewModel {
    private MutableLiveData<PointF> lastTouchDiff = new MutableLiveData<>();
    private MutableLiveData<Float> cameraX = new MutableLiveData<>();
    private MutableLiveData<Float> cameraY = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPlayerAbleMoveX = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPlayerAbleMoveY = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkingPlayerEnemyCollision = new MutableLiveData<>();
    private PlayingLogic playingLogic = new PlayingLogic();

    public LiveData<PointF> getLastTouchDiff() {
        return lastTouchDiff;
    }

    public LiveData<Boolean> getIsPlayerAbleMoveX() {
        return isPlayerAbleMoveX;
    }
    public LiveData<Boolean> getIsPlayerAbleMoveY() {
        return isPlayerAbleMoveY;
    }

    public LiveData<Float> getCameraX() {
        return cameraX;
    }
    public LiveData<Float> getCameraY() {
        return cameraY;
    }

    public void setIsPlayerAbleMoveX(boolean playerAbleMove) {
        isPlayerAbleMoveX.postValue(playerAbleMove);
    }
    public void setIsPlayerAbleMoveY(boolean playerAbleMove) {
        isPlayerAbleMoveY.postValue(playerAbleMove);
    }
    public void setLastTouchDiff(PointF lastTouchDiff) {
        this.lastTouchDiff.setValue(lastTouchDiff);
    }

    public void checkingPlayerEnemyCollision() {
        this.checkingPlayerEnemyCollision.postValue(true);
    }


    public MutableLiveData<Boolean> getCheckingPlayerEnemyCollision() {
        return checkingPlayerEnemyCollision;
    }

    public boolean checkPlayerAbleMoveX(
            boolean attacking, MapManager mapManager,
            PointF delta, PointF camera
    ) {
        return playingLogic.checkPlayerAbleMoveX(
                attacking, mapManager, delta, camera
        );
    }
    public boolean checkPlayerAbleMoveY(
            boolean attacking, MapManager mapManager,
            PointF delta, PointF camera
    ) {
        return playingLogic.checkPlayerAbleMoveY(
                attacking, mapManager, delta, camera
        );
    }

    public boolean checkIntoWallX(MapManager mapManager, PointF camera) {
        return playingLogic.checkIntoWallX(mapManager, camera);
    }

    public boolean checkIntoWallY(MapManager mapManager, PointF camera) {
        return playingLogic.checkIntoWallY(mapManager, camera);
    }

    public void checkAttack(boolean attacking, RectF attackBox,
                            MapManager mapManager,
                            float cameraX, float cameraY) {
        playingLogic.checkAttack(attacking, attackBox, mapManager, cameraX, cameraY);
    }

    public void checkItems(MapManager mapManager, float cameraX, float cameraY) {
        playingLogic.checkItems(mapManager, cameraX, cameraY);
    }

    public void checkAttackByEnemies(RectF playerHitBox,
                                     MapManager mapManager,
                                     float cameraX, float cameraY) {
        playingLogic.checkAttackByEnemies(playerHitBox, mapManager, cameraX, cameraY);
    }

    public boolean ableMoveWhenOverlap() {
        return Player.getInstance().ableMoveWhenOverlap();
    }

    public void updateZombies(MapManager mapManager, double delta, float cameraX, float cameraY) {
        playingLogic.updateZombies(mapManager, delta, cameraX, cameraY);
    }



    public void playingUiTouchEvent(MotionEvent event, PlayingUI playingUI) {
        playingUI.touchEvent(event);
    }
    public void playingUiDrawUi(Canvas c, PlayingUI playingUI) {
        playingUI.drawUI(c);
    }


    public void pauseUiTouchEvent(MotionEvent event, PauseUI pauseUI) {
        pauseUI.touchEvent(event);
    }
    public void pauseUiDrawUi(Canvas c, PauseUI pauseUI) {
        pauseUI.drawUI(c);
    }


    public void bookUiTouchEvent(MotionEvent event, BookUI bookUI) {
        bookUI.touchEvent(event);
    }
    public void bookUiDrawUi(Canvas c, BookUI bookUI) {
        bookUI.drawUI(c);
    }


    public void drawThingOnMap(Canvas c, MapManager mapManager) {
        mapManager.draw(c);
    }










}