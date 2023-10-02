package com.example.myapplication.gmaestates;

import android.app.Activity;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.myapplication.R;
import com.example.myapplication.loopVideo.videos.MenuBackground;
import com.example.myapplication.helper.interfaces.GameStateInterFace;
import com.example.myapplication.main.Game;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.ui.ButtonImage;
import com.example.myapplication.ui.CustomButton;
import com.example.myapplication.ui.GameImages;


public class Menu extends BaseState implements GameStateInterFace {
    private CustomButton btnStart;
    private CustomButton btnExit;


//    private int menuX = MainActivity.GAME_WIDTH / 2 - GameImages.MAINMENU_MENUBG.getImage().getWidth() / 2; //居中
//    private int menuY = MainActivity.GAME_HEIGHT / 2 - GameImages.MAINMENU_MENUBG.getImage().getHeight() / 2;
//    private  int btnStartX = menuX + GameImages.MAINMENU_MENUBG.getImage().getWidth() / 2 - ButtonImage.MENU_START.getWidth() / 2;
//    private int btnStartY = menuY + 100;

    private int btnStartX = MainActivity.GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
    private int btnStartY = MainActivity.GAME_HEIGHT - ButtonImage.MENU_START.getHeight() - 10;

    private int btnExitX = MainActivity.GAME_WIDTH - ButtonImage.MENU_START.getWidth() - 10;
    private int btnExitY = 10;






    private MenuBackground menuBackground;

    public Menu(Game game) {
        super(game);
        menuBackground = new MenuBackground();


        btnStart = new CustomButton(btnStartX, btnStartY, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());

        btnExit = new CustomButton(btnExitX, btnExitY, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());


    }

    @Override
    public void update(double delta) {
        menuBackground.update(delta);
    }

    @Override
    public void render(Canvas c) {
        c.drawBitmap(
                menuBackground.getGameVideoType().getSprite(0, menuBackground.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                menuBackground.getHitBox().left, menuBackground.getHitBox().top, null
        );




//        c.drawBitmap(
//                GameImages.CONFIG.getImage(),
//                menuX,
//                menuY,
//                null
//        );




        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnStart.isPushed()), btnStart.getHitbox().left, btnStart.getHitbox().top, null
        );

        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnExit.isPushed()), btnExit.getHitbox().left, btnExit.getHitbox().top, null
        );







    }



    @Override
    public void touchEvents(MotionEvent event) {
        btnStartAction(event);
        btnExitAction(event);

    }

    private boolean isIn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }

    private void btnStartAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btnStart)) //when pressed button
                btnStart.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnStart)) //check if release inside area of button
                if (btnStart.isPushed()) //check if click start in area of button
                    game.setCurrentGameState(Game.GameState.CONFIG);
            btnStart.setPushed(false);
        }
    }

    private void btnExitAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btnExit)) //when pressed button
                btnExit.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnExit)) //check if release inside area of button
                if (btnExit.isPushed()) //check if click start in area of button
                    ((Activity) MainActivity.getGameContext()).finish();
            btnExit.setPushed(false);
        }
    }







}
