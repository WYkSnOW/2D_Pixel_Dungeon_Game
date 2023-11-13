package com.example.myapplication.View.main.gameStates;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.enemies.RunningZombie;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.ViewModel.gameStatesVideoModel.MenuViewModel;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_HEIGHT;

public class Menu extends BaseState implements GameStateInterFace {
    private CustomButton btnStart;
    private CustomButton btnExit;
    private RunningZombie runningZombie;
    private GameAnimation menuBackground;
    private final MenuViewModel viewModel;

    public Menu(Game game, Context context) {
        super(game);
        initMenu();

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MenuViewModel.class);
        viewModel.getStartButtonClicked().observe((LifecycleOwner) context,
            new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean clicked) {
                    if (clicked) {
                        viewModel.btnStartRespond(game);
                    }
                }
            });

        viewModel.getExitButtonClicked().observe((LifecycleOwner) context,
                new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean clicked) {
                    if (clicked) {
                        viewModel.btnExitRespond(context);
                    }
                }
            });
    }

    private void initMenu() {
        menuBackground = new GameAnimation(
                0,
                0,
                GAME_WIDTH,
                GAME_HEIGHT,
                GameVideos.MAIN_BACK_VIDEO
        );

        runningZombie = new RunningZombie(
                new PointF(
                        0,
                        GAME_HEIGHT - GameCharacters.ZOMBIE.getCharacterHeight()
                )
        );

        btnStart = new CustomButton(
                GameConstants.UiLocation.START_BTN_POS_X,
                GameConstants.UiLocation.START_BTN_POS_Y,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );

        btnExit = new CustomButton(
                GameConstants.UiLocation.EXIT_BTN_POS_X,
                GameConstants.UiLocation.EXIT_BTN_POS_Y,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );
    }

    @Override
    public void update(double delta) {
        if (game.getCurrentGameState() != Game.GameState.MENU) {
            return;
        }
        menuBackground.update();
        runningZombie.update(delta);
    }

    @Override
    public void render(Canvas c) {
        c.drawBitmap(
                menuBackground.getGameVideoType().getSprite(0, menuBackground.getAniIndex()),
                menuBackground.getHitBox().left,
                menuBackground.getHitBox().top,
                null
        );

        c.drawBitmap(
                runningZombie.getGameCharType().getSprite(
                        runningZombie.getFaceDir(), runningZombie.getAniIndex()
                ),
                runningZombie.getHitBox().left,
                runningZombie.getHitBox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnStart.isPushed()),
                btnStart.getHitbox().left,
                btnStart.getHitbox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnExit.isPushed()),
                btnExit.getHitbox().left,
                btnExit.getHitbox().top,
                null
        );
    }

    @Override
    public void touchEvents(MotionEvent event) {
        btnStartAction(event);
        btnExitAction(event);
    }

    private void btnStartAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, btnStart)) {
                btnStart.setPushed(true);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, btnStart)) {
                if (btnStart.isPushed()) {
                    //game.setCurrentGameState(Game.GameState.CONFIG);
                    viewModel.onBtnStartClicked();
                }
            }
            btnStart.setPushed(false);
        }
    }

    private void btnExitAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, btnExit)) {
                btnExit.setPushed(true);
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, btnExit)) {
                if (btnExit.isPushed()) {
                    //((Activity) MainViewModel.getGameContext()).finish();
                    viewModel.onBtnExitClicked();
                }
            }
            btnExit.setPushed(false);
        }
    }
}