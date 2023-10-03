package com.example.myapplication.Model.ui;

import static com.example.myapplication.View.main.MainActivity.gameHeight;
import static com.example.myapplication.View.main.MainActivity.gameWidth;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.myapplication.ViewModel.gmaestates.Playing;
import com.example.myapplication.View.main.MainActivity;

public class PlayingUI {

    //For UI

    private final PointF joystickCenterPos = new PointF(250, gameHeight - 250); //x和y是圆中心位置，可调整. radius即半径
    private final PointF attackBtnCenterPos = new PointF(gameWidth - 250, gameHeight - 250);
    private final  int radius = 150;
    private final Paint circlePaint;




    //For Multitouch
    private int joystickPointerId = -1;
    private int attackBtnPointerId = -1;
    private boolean touchDown;

    private CustomButton btnMenu;
    public final Playing playing;


    private CustomButton btnConfig;

    private  int configStartX = MainActivity.gameWidth - ButtonImage.MENU_START.getWidth();
    private int configStartY = gameHeight - ButtonImage.MENU_START.getHeight();

    public PlayingUI(Playing playing) {
        this.playing = playing;


        circlePaint = new Paint();
        circlePaint.setColor(Color.GRAY);
        //circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAlpha(100);
        circlePaint.setStrokeWidth(5);

        btnMenu = new CustomButton(5, 5, ButtonImage.PLAYING_MENU.getWidth(), ButtonImage.PLAYING_MENU.getHeight());
        btnConfig = new CustomButton(configStartX, configStartY, ButtonImage.MENU_START.getWidth(), ButtonImage.PLAYING_MENU.getHeight());

    }



    public void drawUI(Canvas c) {
        c.drawCircle(
                joystickCenterPos.x,
                joystickCenterPos.y,
                radius,
                circlePaint
        );

        c.drawCircle(
                attackBtnCenterPos.x,
                attackBtnCenterPos.y,
                radius,
                circlePaint
        );


        c.drawBitmap(
                ButtonImage.PLAYING_MENU.getBtnImg(btnMenu.isPushed(btnMenu.getPointerId())),
                btnMenu.getHitbox().left,
                btnMenu.getHitbox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnConfig.isPushed(btnConfig.getPointerId())),
                btnConfig.getHitbox().left,
                btnConfig.getHitbox().top,
                null
        );
        //if(touchDown) { //初始点击点在圆环内且并未松开鼠标触发，松开光标被刷新掉
        //    c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint); //画出光标与圆形的三角形
        //    c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
        //    c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        //}
    }



    private boolean isInsideRadius(PointF eventPos, PointF circle) {
        float a = Math.abs(eventPos.x - circle.x); //找到点击坐标与触控圈圆心的距离
        float b = Math.abs(eventPos.y - circle.y);
        float c = (float) Math.hypot(a, b); //a与b的斜线

        return c <= radius;//光标在圆环内部
    }
    private boolean checkInsideJoyStick(PointF eventPos, int pointerId) {
        if (isInsideRadius(eventPos, joystickCenterPos)) {
            touchDown = true; //初始点击点在圆环内部
            joystickPointerId = pointerId;
            return true;
        }
        return false;
    }
    private boolean checkInsideAttackBtn(PointF eventPos) {
        return isInsideRadius(eventPos, attackBtnCenterPos);
    }


    public void touchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);

        final PointF eventPos = new PointF(event.getX(actionIndex), event.getY(actionIndex));



        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {

            if (checkInsideJoyStick(eventPos, pointerId)) { //光标在圆环内部
                touchDown = true; //初始点击点在圆环内部
            } else if (checkInsideAttackBtn(eventPos)) {
                if(attackBtnPointerId < 0) {
                    playing.setAttacking(true);
                    attackBtnPointerId = pointerId;
                }
                //System.out.println("INSIDE attack");

            } else {
                if (isIn(eventPos, btnMenu)) {
                    btnMenu.setPushed(true, pointerId);
                }
                if (isIn(eventPos, btnConfig)) {
                    btnConfig.setPushed(true, pointerId);
                }
                //game.setCurrentGameState(Game.GameState.END);
            }
        } else if (action == MotionEvent.ACTION_MOVE) { //点击后移动光标

            if (touchDown) { //只有点击圆环内部而后滑出可以操纵

                for (int i = 0; i < event.getPointerCount(); i++) {
                    if (event.getPointerId(i) == joystickPointerId) {
                        float xDiff = event.getX(i) - joystickCenterPos.x; //负数意味点击点x值在圆心x值左边（小于），即角色应该左移，反之右移
                        float yDiff = event.getY(i) - joystickCenterPos.y;
                        playing.setPlayerMoveTrue(new PointF(xDiff, yDiff)); //传输进入控制板中决定玩家是否移动的function
                    }
                }

            }



        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) { //松开光标

            if(pointerId == joystickPointerId) {
                resetJoystickButton();
            } else {
                if (isIn(eventPos, btnMenu)) {
                    if (btnMenu.isPushed(pointerId)) {
                        resetJoystickButton();
                        playing.setGameStateToMenu();
                    }
                }

                if (isIn(eventPos, btnConfig)) {
                    if (btnConfig.isPushed(pointerId)) {
                        resetJoystickButton();
                        playing.setGameStateToEnd();
                    }
                }
            }



            btnConfig.unPush(pointerId);
            btnMenu.unPush(pointerId);

            if (pointerId == attackBtnPointerId) {
                playing.setAttacking(false);
                attackBtnPointerId = -1;
            }
        }
    }


    private void resetJoystickButton() {
        touchDown = false; //松开光标即操作消失
        playing.setPlayerMoveFalse();
        joystickPointerId = -1;
    }

    private boolean isIn(PointF eventPos, CustomButton b) {
        return b.getHitbox().contains(eventPos.x, eventPos.y);
    }
}
