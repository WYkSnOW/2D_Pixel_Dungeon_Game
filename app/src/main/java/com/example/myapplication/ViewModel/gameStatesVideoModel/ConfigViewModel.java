package com.example.myapplication.ViewModel.gameStatesVideoModel;

import android.app.Activity;
import android.view.MotionEvent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.gameStatesLogic.ConfigLogic;
import com.example.myapplication.Model.helper.HelpMethods;
import com.example.myapplication.Model.loopVideo.VideoFrame;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.ViewModel.MainViewModel;


public class ConfigViewModel extends ViewModel {
    private MutableLiveData<String> playerName = new MutableLiveData<>();
    private MutableLiveData<Integer> difficulty = new MutableLiveData<>();
    private MutableLiveData<Integer> characterChoice = new MutableLiveData<>();
    private MutableLiveData<Boolean> configButtonClicked = new MutableLiveData<>();
    private MutableLiveData<Boolean> pickTeresa = new MutableLiveData<>();
    private MutableLiveData<Boolean> pickWitch = new MutableLiveData<>();
    private MutableLiveData<Boolean> pickWarrior = new MutableLiveData<>();
    private MutableLiveData<Boolean> pickDifficulty = new MutableLiveData<>();

    private ConfigLogic configLogic = new ConfigLogic();
    public LiveData<String> getPlayerName() {
        return playerName;
    }

    public LiveData<Integer> getDifficulty() {
        return difficulty;
    }

    public LiveData<Integer> getCharacterChoice() {
        return characterChoice;
    }

    public LiveData<Boolean> getConfigButtonClicked() {
        return configButtonClicked;
    }
    public LiveData<Boolean> getPickTeresa() {
        return pickTeresa;
    }
    public LiveData<Boolean> getPickWitch() {
        return pickWitch;
    }
    public LiveData<Boolean> getPickWarrior() {
        return pickWarrior;
    }
    public LiveData<Boolean> getPickDifficulty() {
        return pickDifficulty;
    }

    public void setPlayerName(String name) {
        playerName.setValue(name);
    }

    public void setDifficulty(int diff) {
        difficulty.setValue(diff);
    }

    public void setCharacterChoice(int choice) {
        characterChoice.setValue(choice);
    }



    public void onBtnConfigClicked() {
        configButtonClicked.setValue(true);
    }
    public void onPickTeresa() {
        pickTeresa.setValue(true);
    }
    public void onPickWitch() {
        pickWitch.setValue(true);
    }
    public void onPickWarrior() {
        pickWarrior.setValue(true);
    }
    public void onPickDifficulty() {
        pickDifficulty.setValue(true);
    }


    public boolean isNameValid(String name) {
        return configLogic.isNameValid(name);
    }
    public boolean ableStart(boolean selectCharacter, int difficultyChoice, boolean validName) {
        return configLogic.ableStart(selectCharacter, difficultyChoice, validName);
    }
    public boolean isInBtn(MotionEvent e, CustomButton b) {
        return HelpMethods.isInBtn(e, b);
    }
    public boolean isInCharacter(MotionEvent e, VideoFrame b) {
        return configLogic.isInCharacter(e, b);
    }

    public void initPlayerCharacter(int characterChoice) {
        configLogic.initPlayerCharacter(characterChoice);
    }
    public void cleanUi() {
        HelpMethods.cleanUi((Activity) MainViewModel.getGameContext());
    }

    public void btnConfigRespond(
            Game game,
            String currentNameText,
            int difficultyChoice
    ) {
        configLogic.btnConfigRespond(game, currentNameText, difficultyChoice);
    }

    public void loopDifficulty(int difficultyChoice) {
        setDifficulty(configLogic.loopDifficultyChoice(difficultyChoice));
    }


    public boolean nameLengthBelowLimit(String name) {
        return configLogic.nameLengthBelowLimit(name);
    }
}
