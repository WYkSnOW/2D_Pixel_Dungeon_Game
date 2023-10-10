package com.example.myapplication.ViewModel.gameStatesVideoModel;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.helper.HelpMethods;
import com.example.myapplication.Model.ui.CustomButton;

public class MenuViewModel extends ViewModel {
    private MutableLiveData<Boolean> startButtonClicked = new MutableLiveData<>();
    private MutableLiveData<Boolean> exitButtonClicked = new MutableLiveData<>();

    public LiveData<Boolean> getStartButtonClicked() {
        return startButtonClicked;
    }

    public LiveData<Boolean> getExitButtonClicked() {
        return exitButtonClicked;
    }

    public void onBtnStartClicked() {
        startButtonClicked.setValue(true);
    }

    public void onBtnExitClicked() {
        exitButtonClicked.setValue(true);
    }

    public boolean isInBtn(MotionEvent e, CustomButton b) {
        return HelpMethods.isInBtn(e, b);
    }

    public void btnStartRespond(Game game) {
        game.setCurrentGameState(Game.GameState.CONFIG);
    }
    public void btnExitRespond(Context context) {
        ((Activity) context).finish();
    }

}