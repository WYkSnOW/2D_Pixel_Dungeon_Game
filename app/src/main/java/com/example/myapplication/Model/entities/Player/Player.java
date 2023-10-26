package com.example.myapplication.Model.entities.Player;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.myapplication.Model.entities.Character;
import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharThree;
import com.example.myapplication.Model.entities.Player.playerStartegy.PlayerCharStrategy;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.leaderBoard.Score.Score;
import com.example.myapplication.Model.loopVideo.GameAnimation;
import com.example.myapplication.R;

public class Player extends Character {

    private long lastUpdate;
    private int gameTime;
    private String playerName;
    private int difficulty;
    private int startingHealth;
    private int currentHealth;
    private int currentScore;
    private boolean winTheGame;
    private static Player instance;
    private RectF attackBox;
    private int attackWidth;
    private int attackHeight;
    private Paint hitBoxPaint = new Paint();
    private GameAnimation attackAffect;
    private PlayerCharStrategy playerCharStrategy;
    private boolean attacking;
    private PlayerStates playerStates;





    public Player(GameCharacters characterChoice) {
        super(new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2), characterChoice);
        initializeGameTime();
        winTheGame = false;
        attackBox = new RectF(0, 0, 0, 0);

        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);

        playerCharStrategy = new CharOne();
        attacking = false;
        playerStates = PlayerStates.IDLE;
    }

    public void setCharStrategy(PlayerCharStrategy playerCharStrategy) {
        this.playerCharStrategy = playerCharStrategy;
        playerCharStrategy.initStrategy();
    }

    public void update(double delta) {
        if (attackAffect != null) {
            attackAffect.update();
        }

        //if (movePlayer) {
        updatePlayerAnim();
        //}
        updateGameTime();
        updateGameScore();
        updateAtkBox();

        //playerCharStrategy.updateCharAtkBox();
        //attackAffect.update(delta);
    }

    protected void updatePlayerAnim() {
        aniTick++;
        if (aniTick >= GameConstants.Animation.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= playerCharStrategy.getAnimMaxIndex(playerStates)) {
                aniIndex = 0;
            }
        }
    }

    public void drawPlayer(Canvas c) {
        playerCharStrategy.drawPlayer(c);
    }
    public void updateAtkBox() {
        playerCharStrategy.updateCharAtkBox();
    }
    public void drawAtk(Canvas c) {
        playerCharStrategy.drawAttackBox(c);
    }

    private void resetPlayer() {
        initializeGameTime();
        setDifficulty(difficulty);
        winTheGame = false;
    }
    public synchronized void setCharacterChoice(GameCharacters characterChoice) {
        changeAnim(characterChoice);

        resetPlayer();
    }

    public synchronized void changeAnim(GameCharacters characterChoice) {
        this.gameCharType = characterChoice;
        PointF pos = new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2);

        float width = gameCharType.getCharacterWidth() - gameCharType.getHitBoxOffSetX();
        float height = gameCharType.getCharacterHeight() - gameCharType.getHitBoxOffSetY();
        this.hitBox = new RectF(pos.x, pos.y, pos.x + width, pos.y + height);

        this.characterWidth = gameCharType.getCharacterWidth();
        this.characterHeight = gameCharType.getCharacterHeight();
        this.hitBoxOffsetX = gameCharType.getHitBoxOffSetX();
        this.hitBoxOffSetY = gameCharType.getHitBoxOffSetY();
    }

    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player(GameCharacters.WARRIOR);
        }
        return instance;
    }



    public int getCurrentScore() {
        return currentScore;
    }

    private void updateGameScore() {
        int tempScore = 20 - gameTime;
        this.currentScore = checkScoreAboveZero(tempScore);
    }

    public static int checkScoreAboveZero(int tempScore) {
        return Math.max(tempScore, 0);
    }

    private void updateGameTime() {
        long now = System.currentTimeMillis();
        if (now - lastUpdate >= 1000) { //increase gameTime every 100 milli second(1 second)
            gameTime++;
            lastUpdate += 1000;
        }
    }

    public void initializeGameTime() {
        gameTime = 0;
        lastUpdate = System.currentTimeMillis();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        this.startingHealth = calculateStartingHealth(difficulty);
        this.currentHealth = startingHealth;
    }

    public static int calculateStartingHealth(int difficulty) {
        if (difficulty > 0) {
            return (int) (100.0 / difficulty);
        } else {
            return  100;
        }
    }


    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getDifficulty() {
        return difficulty;

    }
    public int getCurrentHealth() {
        return currentHealth;
    }

    public Score sumbitScore() {
        return new Score(currentScore, difficulty, playerName, true);
    }

    public boolean isWinTheGame() {
        return winTheGame;
    }

    public void setWinTheGame(boolean winTheGame) {
        this.winTheGame = winTheGame;
    }

    public void setAttackBox(RectF attackBox) {
        this.attackBox = attackBox;
    }

    public void setAttackWidth(int attackWidth) {
        this.attackWidth = attackWidth;
    }

    public void setAttackHeight(int attackHeight) {
        this.attackHeight = attackHeight;
    }

    public int getAttackWidth() {
        return attackWidth;
    }

    public int getAttackHeight() {
        return attackHeight;
    }

    public Paint getHitBoxPaint() {
        return hitBoxPaint;
    }

    public RectF getAttackBox() {
        return attackBox;
    }

    public void setAttackAffect(GameAnimation attackAffect) {
        this.attackAffect = attackAffect;
    }

    public GameAnimation getAttackAffect() {
        return attackAffect;
    }

    public PlayerCharStrategy getCurrentCharStrategy() {
        return playerCharStrategy;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
        if (attacking) {
            setPlayerStates(PlayerStates.ATTACK);
        } else {
            backToIdleState();
        }
    }

    public boolean isAttacking() {
        return attacking;
    }

    public PlayerStates getPlayerStates() {
        return playerStates;
    }

    public void setPlayerStates(PlayerStates playerStates) {
        if (this.playerStates != playerStates) {
            this.playerStates = playerStates;
        }
    }

    public void backToIdleState() {
        playerStates = PlayerStates.IDLE;
    }
}
