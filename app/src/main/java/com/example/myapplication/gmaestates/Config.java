package com.example.myapplication.gmaestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.myapplication.entities.GameCharacters;
import com.example.myapplication.entities.Player;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.GameStateInterFace;
import com.example.myapplication.loopVideo.videos.ConfigBackground;
import com.example.myapplication.loopVideo.videos.MenuBackground;
import com.example.myapplication.main.Game;
import com.example.myapplication.main.MainActivity;
import com.example.myapplication.ui.ButtonImage;
import com.example.myapplication.ui.CustomButton;


public class Config extends BaseState implements GameStateInterFace {
    private Paint paint = new Paint();
    private CustomButton btnConfig;

    private  int configStartX = MainActivity.GAME_WIDTH - ButtonImage.MENU_START.getWidth();
    private int configStartY = MainActivity.GAME_HEIGHT - ButtonImage.MENU_START.getHeight();

    private ConfigBackground configBackground;



    private CustomButton btntemp1;
    private CustomButton btntemp2;
    private CustomButton btntemp3;
    private int characterChoice = 0;



    public Config(Game game) {
        super(game);
        paint = new Paint();
        paint.setTextSize(200);
        paint.setColor(Color.WHITE);
        btnConfig = new CustomButton(configStartX, configStartY, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());

        configBackground = new ConfigBackground();

        btntemp1 = new CustomButton(0, 100, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());
        btntemp2 = new CustomButton(400, 100, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());
        btntemp3 = new CustomButton(800, 100, ButtonImage.MENU_START.getWidth(), ButtonImage.MENU_START.getHeight());
    }

    @Override
    public void update(double delta) {
        configBackground.update(delta);
    }

    @Override
    public void render(Canvas c) {
        c.drawBitmap(
                configBackground.getGameVideoType().getSprite(0, configBackground.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                configBackground.getHitBox().left, configBackground.getHitBox().top, null
        );



        c.drawText("CONFIG", 800, 200, paint);
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnConfig.isPushed()), btnConfig.getHitbox().left, btnConfig.getHitbox().top, null
        );


        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btntemp1.isPushed()), btntemp1.getHitbox().left, btntemp1.getHitbox().top, null
        );
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btntemp2.isPushed()), btntemp2.getHitbox().left, btntemp2.getHitbox().top, null
        );
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btntemp3.isPushed()), btntemp3.getHitbox().left, btntemp3.getHitbox().top, null
        );

    }

    @Override
    public void touchEvents(MotionEvent event) {
        btnConfigAction(event);

        temp1(event);
        temp2(event);
        temp3(event);

    }

    private boolean isIn(MotionEvent e, CustomButton b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }


    private void btnConfigAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btnConfig)) //when pressed button
                btnConfig.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btnConfig)) //check if release inside area of button
                if (btnConfig.isPushed()) { //check if click start in area of button

                    if (characterChoice == 1) {
                        game.getPlaying().setPlayer(new Player(GameCharacters.ELF));
                        game.getPlaying().getPlayer().setPlayerWidth(GameConstants.PlayerAnimDefault.ELF_WIDTH);
                        game.getPlaying().getPlayer().setPlayerHeight(GameConstants.PlayerAnimDefault.ELF_HEIGHT);
                    } else if (characterChoice == 2) {
                        game.getPlaying().setPlayer(new Player(GameCharacters.KNIGHT));
                        game.getPlaying().getPlayer().setPlayerWidth(GameConstants.PlayerAnimDefault.KNIGHT_WIDTH);
                        game.getPlaying().getPlayer().setPlayerHeight(GameConstants.PlayerAnimDefault.KNIGHT_HEIGHT);
                    } else if (characterChoice == 3) {
                        game.getPlaying().setPlayer(new Player(GameCharacters.WIZARD));
                        game.getPlaying().getPlayer().setPlayerWidth(GameConstants.PlayerAnimDefault.WIZARD_WIDTH);
                        game.getPlaying().getPlayer().setPlayerHeight(GameConstants.PlayerAnimDefault.WIZARD_HEIGHT);
                    } else {
                        game.getPlaying().setPlayer(new Player(GameCharacters.WARRIOR));
                        game.getPlaying().getPlayer().setPlayerWidth(29);
                        game.getPlaying().getPlayer().setPlayerHeight(33);
                    }




                    game.setCurrentGameState(Game.GameState.PLAYING);
                }
            btnConfig.setPushed(false);
        }
    }


    private void temp1(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btntemp1)) //when pressed button
                btntemp1.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btntemp1)) //check if release inside area of button
                if (btntemp1.isPushed()) //check if click start in area of button
                    characterChoice = 1;
            btntemp1.setPushed(false);
        }
    }
    private void temp2(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btntemp2)) //when pressed button
                btntemp2.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btntemp2)) //check if release inside area of button
                if (btntemp2.isPushed()) //check if click start in area of button
                    characterChoice = 2;
            btntemp2.setPushed(false);
        }
    }
    private void temp3(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isIn(event, btntemp3)) //when pressed button
                btntemp3.setPushed(true); //change press state + change picture showing
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isIn(event, btntemp3)) //check if release inside area of button
                if (btntemp3.isPushed()) //check if click start in area of button
                    characterChoice = 3;
            btntemp3.setPushed(false);
        }
    }



}
