package com.example.myapplication.gmaestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.myapplication.helper.interfaces.GameStateInterFace;
import com.example.myapplication.main.Game;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.ui.ButtonImage;
import com.example.myapplication.ui.CustomButton;

public class End extends BaseState implements GameStateInterFace {
    private Paint paint = new Paint();
    private CustomButton btnConfig;
    private  int configStartX = MainActivity.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
    private int configStartY = MainActivity.GAME_HEIGHT- ButtonImage.MENU_START.getHeight();

    public End(Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(200);
        paint.setColor(Color.WHITE);
        btnConfig = new CustomButton(configStartX, configStartY, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Canvas c) {
        c.drawText("End", 800, 200, paint);
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnConfig.isPushed()), btnConfig.getHitbox().left, btnConfig.getHitbox().top, null
        );
    }

    @Override
    public void touchEvents(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btnConfig)) //when pressed button
                btnConfig.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnConfig)) //check if release inside area of button
                if (btnConfig.isPushed()) {//check if click start in area of button
                    game.setCurrentGameState(Game.GameState.MENU);
                }

            btnConfig.setPushed(false);
        }


    }

    private boolean isIn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }
}