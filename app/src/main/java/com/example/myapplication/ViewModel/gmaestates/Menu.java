package com.example.myapplication.ViewModel.gmaestates;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.RunningZombie;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.View.main.Game;
import com.example.myapplication.View.main.MainActivity;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;



public class Menu extends BaseState implements GameStateInterFace {
    private CustomButton btnStart;
    private CustomButton btnExit;
    private int btnStartX = GameConstants.UiLocation.START_BTN_POS_X;
    private int btnStartY = GameConstants.UiLocation.START_BTN_POS_Y;
    private int btnExitX = GameConstants.UiLocation.EXIT_BTN_POS_X;
    private int btnExitY = GameConstants.UiLocation.EXIT_BTN_POS_Y;
    private RunningZombie runningZombie;
    private GameAnimation menuBackground;


    public Menu(Game game) {
        super(game);
        initMenu();

    }
    private void initMenu() {
        menuBackground = new GameAnimation(0, 0, MainActivity.gameWidth, MainActivity.gameHeight, GameVideos.MAIN_BACK_VIDEO);

        runningZombie = new RunningZombie(
                new PointF(
                        0,
                        MainActivity.gameHeight - GameCharacters.ZOMBIE.getCharacterHeight()
                )
        );
        btnStart = new CustomButton(//Btn that lead to config screen(another game state)
                btnStartX,
                btnStartY,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );
        btnExit = new CustomButton(//Btn that left the Game
                btnExitX,
                btnExitY,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );
    }

    @Override
    public void update(double delta) {
        menuBackground.update(delta);
        runningZombie.update(delta);
    }

    @Override
    public void render(Canvas c) {
        c.drawBitmap(
                menuBackground.getGameVideoType().getSprite(0, menuBackground.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                menuBackground.getHitBox().left,
                menuBackground.getHitBox().top,
                null
        );
        c.drawBitmap(
                runningZombie.getGameCharType().getSprite(runningZombie.getDrawDir(), runningZombie.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                runningZombie.getHitBox().left,
                runningZombie.getHitBox().top,
                null
        );
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
            if (isIn(event, btnStart)) { //when pressed button
                btnStart.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnStart)) { //check if release inside area of button
                if (btnStart.isPushed()) { //check if click start in area of button
                    game.setCurrentGameState(Game.GameState.CONFIG);
                }
            }
            btnStart.setPushed(false);
        }
    }

    private void btnExitAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btnExit)) { //when pressed button
                btnExit.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnExit)) { //check if release inside area of button
                if (btnExit.isPushed()) { //check if click start in area of button
                    ((Activity) MainActivity.getGameContext()).finish();
                }
            }
            btnExit.setPushed(false);
        }
    }







}
