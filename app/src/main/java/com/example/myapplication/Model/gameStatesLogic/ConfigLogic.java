package com.example.myapplication.Model.gameStatesLogic;

import android.view.MotionEvent;

import com.example.myapplication.Model.coreLogic.Game;
import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharThree;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharTwo;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.loopVideo.VideoFrame;

public class ConfigLogic {
    //check if player put in a in valid name
    public boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        if (name.equals("")) {
            return false;
        }
        boolean r = false;
        for (int i = 0; i < name.length(); i++) {
            if (!(name.charAt(i) == ' ')) {
                r = true;
            }
        }
        if (name.charAt(0) == ' ') {
            r = false;
        }
        return r;
    }
    public boolean nameLengthBelowLimit(String name) {
        return name.length() <= 15;
    }
    public boolean ableStart(boolean selectCharacter, int difficultyChoice, boolean validName) {
        return selectCharacter && difficultyChoice > 0 && validName;
    }
    public boolean isInCharacter(MotionEvent e, VideoFrame b) {
        return b.getHitbox().contains(e.getX(), e.getY());
    }
    public void initPlayerCharacter(int characterChoice) {
        if (characterChoice == 1) {
            Player.getInstance().setCharStrategy(new CharOne());
        } else if (characterChoice == 2) {
            Player.getInstance().setCharStrategy(new CharTwo());
        } else if (characterChoice == 3) {
            Player.getInstance().setCharStrategy(new CharThree());
        }
    }
    public int loopDifficultyChoice(int difficultyChoice) {
        if (difficultyChoice < 3) {
            return difficultyChoice + 1;
        }
        return 1;
    }


    public void btnConfigRespond(
            Game game,
            String currentNameText,
            int difficultyChoice
    ) {

        if (Player.getInstance().getGameCharType() == GameCharacters.CENTAUR) {
            Player.getInstance().setCharStrategy(new CharOne());
        } else if (Player.getInstance().getGameCharType()
                == GameCharacters.WITCH2) {
            Player.getInstance().setCharStrategy(new CharTwo());
        } else {
            Player.getInstance().setCharStrategy(new CharThree());
        }


        Player.getInstance().setPlayerName(currentNameText);
        Player.getInstance().setDifficulty(difficultyChoice);
        Player.getInstance().setCurrentStates(PlayerStates.IDLE);
        game.getPlaying().getMapManager().initEnemyHealthWithDiff();
        game.setCurrentGameState(Game.GameState.PLAYING);

    }


}
