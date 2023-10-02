package com.example.myapplication.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.myapplication.gmaestates.Playing;
import com.example.myapplication.main.Game;
import com.example.myapplication.main.MainActivity;

public class PlayingUI {

    //For UI
    private float xCenter = 250, yCenter = 800, radius = 150; //x和y是圆中心位置，可调整. radius即半径
    private Paint circlePaint;

    private float xTouch, yTouch;
    private boolean touchDown;


    private CustomButton btnMenu;
    public final Playing playing;


    private CustomButton btnConfig;

    private  int configStartX = MainActivity.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
    private int configStartY = MainActivity.GAME_HEIGHT- ButtonImage.MENU_START.getHeight();

    public PlayingUI(Playing playing) {
        this.playing = playing;
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);

        btnMenu = new CustomButton(5, 5, ButtonImage.PLAYING_MENU.getWidth(), ButtonImage.PLAYING_MENU.getHeight());
        btnConfig = new CustomButton(configStartX, configStartY, ButtonImage.MENU_START.getWidth(), ButtonImage.PLAYING_MENU.getHeight());

    }



    public void drawUI(Canvas c) {
        c.drawCircle(xCenter, yCenter, radius, circlePaint);
        c.drawBitmap(ButtonImage.PLAYING_MENU.getBtnImg(btnMenu.isPushed()), btnMenu.getHitbox().left, btnMenu.getHitbox().top,null);

        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnConfig.isPushed()), btnConfig.getHitbox().left, btnConfig.getHitbox().top, null
        );
        //if(touchDown) { //初始点击点在圆环内且并未松开鼠标触发，松开光标被刷新掉
        //    c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint); //画出光标与圆形的三角形
        //    c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
        //    c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        //}
    }
    public void touchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX(); //找到点击位置的坐标
                float y = event.getY();

                float a = Math.abs(x - xCenter); //找到点击坐标与触控圈圆心的距离
                float b = Math.abs(y - yCenter);
                float c = (float) Math.hypot(a,b); //a与b的斜线

                if(c <= radius) { //光标在圆环内部
                    touchDown = true; //初始点击点在圆环内部
                    xTouch = x;
                    yTouch = y;
                } else {
                    if(isIn(event, btnMenu)) {
                        btnMenu.setPushed(true);
                    }
                    if(isIn(event, btnConfig)) {
                        btnConfig.setPushed(true);
                    }
                    //game.setCurrentGameState(Game.GameState.END);
                }
                break;
            case MotionEvent.ACTION_MOVE: //点击后移动光标
                if(touchDown) { //只有点击圆环内部而后滑出可以操纵
                    xTouch = event.getX();
                    yTouch = event.getY();

                    float xDiff = xTouch - xCenter; //负数意味点击点x值在圆心x值左边（小于），即角色应该左移，反之右移
                    float yDiff = yTouch - yCenter;

                    playing.setPlayerMoveTrue(new PointF(xDiff, yDiff)); //传输进入控制板中决定玩家是否移动的function
                }
                break;
            case MotionEvent.ACTION_UP: //松开光标
                if(isIn(event, btnMenu)) {
                    if (btnMenu.isPushed()) {
                        playing.setGameStateToMenu();
                    }
                }

                if(isIn(event, btnConfig)) {
                    if (btnConfig.isPushed()) {
                        playing.setGameStateToEnd();
                    }
                }

                btnConfig.setPushed(false);
                btnMenu.setPushed(false);
                touchDown = false; //松开光标即操作消失
                playing.setPlayerMoveFalse();
                break;
        }
    }
    private boolean isIn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }
}
