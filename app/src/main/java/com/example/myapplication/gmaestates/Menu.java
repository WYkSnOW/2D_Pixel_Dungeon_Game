package com.example.myapplication.gmaestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.myapplication.helper.interfaces.GameStateInterFace;
import com.example.myapplication.main.Game;


public abstract class Menu extends BaseState implements GameStateInterFace {

    private Paint paint;

    public Menu(Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(200);
        paint.setColor(Color.WHITE);
    }



    @Override
    public void update(double delta) {

    }

    @Override
    public void render(Canvas c) {
        c.drawText("END", 800, 200, paint);
    }

    @Override
    public void touchEvents(MotionEvent event) {
        //if (event.getAction() == MotionEvent.ACTION_DOWN) {
         //   game.setCurrentGameState(Game.GameState.PLAYING);
        //}


    }
}
