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
    private PlayingLogic playingLogic = new PlayingLogic();

    public LiveData<PointF> getLastTouchDiff() {
        return lastTouchDiff;
    }

    public LiveData<Boolean> getIsAttacking() {
        return isAttacking;
    }
    public LiveData<Float> getCameraX() {
        return cameraX;
    }
    public LiveData<Float> getCameraY() {
        return cameraY;
    }
    public void setAttacking(boolean attacking) {
        isAttacking.setValue(attacking);
    }

    public void setLastTouchDiff(PointF lastTouchDiff) {
        this.lastTouchDiff.setValue(lastTouchDiff);
    }
    public void setCameraX(float cameraX) {
        this.cameraX.setValue(cameraX);
    }
    public void setCameraY(float cameraY) {
        this.cameraY.setValue(cameraY);
    }
    public int getPlayerDrawDir(boolean attacking) {
        return playingLogic.getPlayerDrawDir(attacking);
    }
    public void setPlayerAnimDir(float xSpeed, float ySpeed, PointF lastTouchDiff) {
        playingLogic.setPlayerAnimDir(xSpeed, ySpeed, lastTouchDiff);
    }
    public Bitmap getPlayerSprite(boolean attacking) {
        return playingLogic.getPlayerSprite(attacking);
    }
    public float getPlayerLeft() {
        return playingLogic.getPlayerLeft();
    }
    public float getPlayerTop() {
        return playingLogic.getPlayerTop();
    }
    public RectF getPlayerHitbox() {
        return playingLogic.getPlayerHitbox();
    }
    public PointF getEffectPos() {
        return playingLogic.getEffectPos();
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
    public float getEffectRote() {
        return playingLogic.getEffectRote();
    }
    public int offSetX() {
        return playingLogic.offSetX();
    }
    public void checkAttack(boolean attacking, RectF attackBox,
                            MapManager mapManager,
                            float cameraX, float cameraY) {
        playingLogic.checkAttack(attacking, attackBox, mapManager, cameraX, cameraY);
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
