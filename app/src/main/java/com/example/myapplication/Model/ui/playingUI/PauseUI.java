package com.example.myapplication.Model.ui.playingUI;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;


import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.View.main.gameStates.Playing;

public class PauseUI {
    private CustomButton btnResume;
    private final Playing playing;
    public PauseUI(Playing playing) {
        this.playing = playing;


        btnResume = new CustomButton(
                GAME_WIDTH - ButtonImage.PLAYING_RESUME.getWidth() - 5,
                5,
                ButtonImage.PLAYING_RESUME.getWidth(),
                ButtonImage.PLAYING_RESUME.getHeight()
        );
    }


    public void drawUI(Canvas c) {
        drawBtn(c);


        //if(touchDown) { //初始点击点在圆环内且并未松开鼠标触发，松开光标被刷新掉
        //    c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint); //画出光标与圆形的三角形
        //    c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
        //    c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        //}
    }


    private void drawBtn(Canvas c) {
        c.drawBitmap(
                ButtonImage.PLAYING_RESUME.getBtnImg(btnResume.isPushed(btnResume.getPointerId())),
                btnResume.getHitbox().left,
                btnResume.getHitbox().top,
                null
        );

    }


    public void touchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);
        final PointF eventPos = new PointF(event.getX(actionIndex), event.getY(actionIndex));


        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {


            if (isIn(eventPos, btnResume)) {
                btnResume.setPushed(true, pointerId);
            }
        } else if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_POINTER_UP) { //松开光标
            if (isIn(eventPos, btnResume)) {
                if (btnResume.isPushed(pointerId)) {
                    playing.changeOnPause();
                }
            }
            btnUpAction(pointerId);
        }

    }



    private void btnUpAction(int pointerId) {
        btnResume.unPush(pointerId);
    }





    private boolean isIn(PointF eventPos, CustomButton b) {
        return b.getHitbox().contains(eventPos.x, eventPos.y);
    }



}
