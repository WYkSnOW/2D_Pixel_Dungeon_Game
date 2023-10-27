package com.example.myapplication.ViewModel.gameStatesVideoModel;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.environments.MapManager;
import com.example.myapplication.Model.gameStatesLogic.PlayingLogic;
import com.example.myapplication.Model.ui.PlayingUI;

public class PlayingViewModel extends ViewModel {
    private MutableLiveData<Boolean> isAttacking = new MutableLiveData<>();
    private MutableLiveData<PointF> lastTouchDiff = new MutableLiveData<>();
    private MutableLiveData<Float> cameraX = new MutableLiveData<>();
    private MutableLiveData<Float> cameraY = new MutableLiveData<>();
    private MutableLiveData<Boolean> isPlayerAbleMove = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkingPlayerEnemyCollision = new MutableLiveData<>();
    private PlayingLogic playingLogic = new PlayingLogic();

    public LiveData<PointF> getLastTouchDiff() {
        return lastTouchDiff;
    }

    public LiveData<Boolean> getIsPlayerAbleMove() {
        return isPlayerAbleMove;
    }

    public LiveData<Float> getCameraX() {
        return cameraX;
    }
    public LiveData<Float> getCameraY() {
        return cameraY;
    }

    public void setIsPlayerAbleMove(boolean playerAbleMove) {
        isPlayerAbleMove.postValue(playerAbleMove);
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

    public boolean checkPlayerAbleMove(
            boolean attacking, MapManager mapManager,
            int pWidth, int pHeight,
            PointF delta, PointF camera
    ) {
        return playingLogic.checkPlayerAbleMove(
                attacking, mapManager, pWidth, pHeight, delta, camera
        );
    }

    public void checkAttack(boolean attacking, RectF attackBox,
                            MapManager mapManager,
                            float cameraX, float cameraY) {
        playingLogic.checkAttack(attacking, attackBox, mapManager, cameraX, cameraY);
    }

    public void checkAttackByEnemies(RectF playerHitBox,
                            MapManager mapManager,
                            float cameraX, float cameraY) {
        playingLogic.checkAttackByEnemies(playerHitBox, mapManager, cameraX, cameraY);
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


}
