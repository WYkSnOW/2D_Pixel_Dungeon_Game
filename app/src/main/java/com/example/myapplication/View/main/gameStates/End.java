package com.example.myapplication.View.main.gameStates;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.leaderBoard.Leaderboard;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.helper.interfaces.GameStateInterFace;
import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.ViewModel.gameStatesVideoModel.EndViewModel;

public class End extends BaseState implements GameStateInterFace {
    private final CustomButton btnRestart;
    private Score currentScore;
    private final EndViewModel viewModel;

    public End(Game game, Context context) {
        super(game);

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
    public void update(double delta) { }
    @Override
    public void touchEvents(MotionEvent event) {
        btnRestartAction(event);
    }
    @Override
    public void render(Canvas c) {
        //c.drawText("End", 800, 200, paint);
        drawBtn(c);
        Leaderboard.getInstance().drawLeaderBoard(c);
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