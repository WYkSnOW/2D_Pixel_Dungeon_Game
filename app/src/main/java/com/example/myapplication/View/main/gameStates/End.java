package com.example.myapplication.View.main.gameStates;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.Model.ui.GameImages;
import com.example.myapplication.ViewModel.gameStatesVideoModel.EndViewModel;

public class End extends BaseState implements GameStateInterFace {
    private final CustomButton btnRestart;
    private final EndViewModel viewModel;
    private final GameAnimation endBackground;
    private final GameAnimation loseScreenBack;

    public End(Game game, Context context) {
        super(game);

        endBackground = new GameAnimation(
                0,
                0,
                GAME_WIDTH,
                GAME_HEIGHT,
                GameVideos.END_BACK_VIDEO
        );

        loseScreenBack = new GameAnimation(
                GAME_WIDTH * -1 / 2,
                GAME_HEIGHT * -1 / 2,
                GAME_WIDTH,
                GAME_HEIGHT,
                GameVideos.LOSE_SCREEN_VIDEO
        );

        btnRestart = new CustomButton(
                GameConstants.UiLocation.END_RESTART_BTN_POS_X,
                GameConstants.UiLocation.END_RESTART_BTN_POS_Y,
                ButtonImage.MENU_START.getWidth(),
                ButtonImage.MENU_START.getHeight()
        );

        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(EndViewModel.class);
        viewModel.getCurrentScore().observe((LifecycleOwner) context, new Observer<Score>() {
            @Override
            public void onChanged(Score score) {
                Leaderboard.getInstance().addPlayerRecord(score);
            }
        });
        viewModel.getRestartButtonClicked().observe((LifecycleOwner) context,
                new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean clicked) {
                    if (clicked) {
                        viewModel.btnRestartRespond(game);
                    }
                }
            });
    }
    @Override
    public void update(double delta) {
        if (game.getCurrentGameState() != Game.GameState.END) {
            return;
        }
        endBackground.update(delta);
        loseScreenBack.update(delta);

        Player.getInstance().update(delta);

    }
    @Override
    public void touchEvents(MotionEvent event) {
        btnRestartAction(event);
    }
    @Override
    public void render(Canvas c) {
        if (game.getCurrentGameState() != Game.GameState.END) {
            return;
        }
        //c.drawText("End", 800, 200, paint);
        drawBackground(c);
        drawUi(c);
        drawBtn(c);
        drawPlayer(c);
        Leaderboard.getInstance().drawLeaderBoard(c);
    }

    public void drawBackground(Canvas c) {
        if (Player.getInstance().isWinTheGame()) {
            c.drawBitmap(
                    endBackground.getGameVideoType().getSprite(0, endBackground.getAniIndex()),
                    endBackground.getHitBox().left,
                    endBackground.getHitBox().top - 150,
                    null
            );

        } else {
            c.drawBitmap(
                    loseScreenBack.getGameVideoType().getSprite(0, loseScreenBack.getAniIndex()),
                    loseScreenBack.getHitBox().left,
                    loseScreenBack.getHitBox().top - 150,
                    null
            );
        }

    }

    public void drawUi(Canvas c) {
        Bitmap resultBar = GameImages.PLAYER_LOSE_BAR.getImage();
        if (Player.getInstance().isWinTheGame()) {
            resultBar = GameImages.PLAYER_WIN_BAR.getImage();
        }

        c.drawBitmap(
                resultBar,
                (int) ((GAME_WIDTH / 2)
                        - (GameImages.PLAYER_WIN_BAR.getWidth() / 2) + 40),
                30,
                null
        );
        c.drawBitmap(
                GameImages.LEADERBOARD.getImage(),
                100,
                180,
                null
        );
        c.drawBitmap(
                GameImages.CURRENT_BOARD.getImage(),
                1880,
                390,
                null
        );
    }

    private void drawPlayer(Canvas c) {
        c.drawBitmap(Player.getInstance().getGameCharType()
                        .getSprite(2, Player.getInstance().getAniIndex()),
                1880 + 50,
                390 + 300,
                null);
    }

    private void drawBtn(Canvas c) {
        c.drawBitmap(
                ButtonImage.MENU_START.getBtnImg(btnRestart.isPushed()),
                btnRestart.getHitbox().left,
                btnRestart.getHitbox().top,
                null
        );
    }

    private void btnRestartAction(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (viewModel.isInBtn(event, btnRestart)) { //when pressed button
                btnRestart.setPushed(true); //change press state + change picture showing
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (viewModel.isInBtn(event, btnRestart)) { //check if release inside area of button
                if (btnRestart.isPushed()) { //check if click start in area of button
                    viewModel.onBtnRestartClicked();
                }
            }
            btnRestart.setPushed(false);
        }
    }
    public void addPlayerRecord(Score score) {
        viewModel.setCurrentScore(score);
    }
}