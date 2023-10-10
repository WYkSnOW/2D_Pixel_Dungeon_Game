package com.example.myapplication.ViewModel.gameStatesVideoModel;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.helper.HelpMethods;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.ui.CustomButton;


public class EndViewModel extends ViewModel {

    private MutableLiveData<Score> currentScore = new MutableLiveData<>();
    private MutableLiveData<Boolean> restartButtonClicked = new MutableLiveData<>();

    public LiveData<Score> getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Score score) {
        currentScore.setValue(score);
    }

    public LiveData<Boolean> getRestartButtonClicked() {
        return restartButtonClicked;
    }

    public void onBtnRestartClicked() {
        restartButtonClicked.setValue(true);
    }

    public boolean isInBtn(MotionEvent e, CustomButton b) {
        return HelpMethods.isInBtn(e, b);
    }

    public void btnRestartRespond(Game game) {
        game.setCurrentGameState(Game.GameState.MENU);
        game.restartGame();
    }

}