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
import com.example.myapplication.Model.entities.Player.projectile.Projectile;
import com.example.myapplication.Model.environments.GameMap;
import com.example.myapplication.Model.helper.GameConstants;
import com.example.myapplication.Model.leaderBoard.Score.Score;

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
    private PlayerCharStrategy playerCharStrategy;
    private boolean attacking;
    private PlayerStates currentStates;
    private boolean onSkill;
    private float baseSpeed;
    private boolean isMakingDamage;
    private boolean ableMakeDamage;
    private boolean projecting;
    private boolean ableProjectile;
    private GameMap currentMap;
    private int baseDamage;
    private boolean invincible;
    private boolean haveInteract;
    private boolean madeInteraction;
    private int defence;

    private boolean overlapEnemy;
    private int overlapDir;



    public Player(GameCharacters characterChoice) {
        super(new PointF(GAME_WIDTH / 2, GAME_HEIGHT / 2), characterChoice);
        attackBox = new RectF(0, 0, 0, 0);

        hitBoxPaint.setStrokeWidth(1);
        hitBoxPaint.setStyle(Paint.Style.STROKE);
        hitBoxPaint.setColor(Color.RED);

        playerCharStrategy = new CharOne();
        difficulty = 1;



        resetPlayer();
    }

    public void setCharStrategy(PlayerCharStrategy playerCharStrategy) {
        this.playerCharStrategy = playerCharStrategy;
        playerCharStrategy.initStrategy();
    }

    public void update(double delta) {


        //if (movePlayer) {
        updatePlayerAnim();
        //}
        updateGameTime();
        updateGameScore();
        updateAtkBox();



    }

    public void updatePlayerAnim() {
        aniTick++;


        if (aniTick >= GameConstants.Animation.ANI_SPEED) {
            aniTick = 0;
            aniIndex++;

            if (aniIndex >= playerCharStrategy.getAnimMaxIndex(currentStates)) {
                if (onSkill) {
                    backToIdleState();
                }
                aniIndex = 0;
            }
        }
    }
    public PointF getPlayerMovement(float xSpeed, float ySpeed, float baseSpeed) {
        return playerCharStrategy.gePlayerMovement(xSpeed, ySpeed, baseSpeed);
    }

    public void drawPlayer(Canvas c) {
        playerCharStrategy.drawPlayer(c);
    }
    public void updateAtkBox() {
        playerCharStrategy.updateAtkBox();
    }
    public void drawAtk(Canvas c) {
        playerCharStrategy.drawAttackBox(c);
    }

    private void resetPlayer() {
        initializeGameTime();
        setDifficulty(difficulty);
        winTheGame = false;
        baseSpeed = 300;
        attacking = false;
        currentStates = PlayerStates.IDLE;
        onSkill = false;
        isMakingDamage = false;
        ableProjectile = false;
        ableMakeDamage = false;
        baseDamage = 0;
        invincible = false;
        haveInteract = false;
        madeInteraction = false;
        overlapEnemy = false;
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
            instance = new Player(GameCharacters.WARRIOR2);
            instance.setCharStrategy(new CharThree());
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




    public void lostHealth(int health, int fromDir) {
        if (!invincible) {
            if (currentHealth > health) {
                this.currentHealth = health;
                setToHurt(fromDir);
            }

        }

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



    public Paint getHitBoxPaint() {
        return hitBoxPaint;
    }

    public RectF getAttackBox() {
        return attackBox;
    }


    public void setAttacking(boolean attacking) {
        if (!onSkill) {
            this.attacking = attacking;
            if (attacking) {
                setCurrentStates(PlayerStates.ATTACK);
            } else {
                backToIdleState();
            }
        }
    }

    public void setProjecting(boolean projecting) {
        if (!onSkill) {
            this.projecting = projecting;
            if (projecting) {
                setCurrentStates(PlayerStates.PROJECTILE);
            } else {
                ableProjectile = false;
                backToIdleState();
            }
        }
    }

    public boolean isProjecting() {
        return projecting;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public PlayerStates getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(PlayerStates currentStates) {
        if (this.currentStates != currentStates) {
            aniIndex = 0;
            this.currentStates = currentStates;
        }
    }

    public void setToHurt(int fromDir) {
        if (!onSkill) {
            onSkill = true;
            invincible = true;

            if (fromDir == faceDir) {
                if (faceDir == GameConstants.FaceDir.RIGHT) {
                    faceDir = GameConstants.FaceDir.LEFT;
                } else {
                    faceDir = GameConstants.FaceDir.RIGHT;
                }
            }

            setCurrentStates(PlayerStates.HURT);

        }

    }

    public void setToDash() {
        onSkill = true;
        invincible = true;
        setCurrentStates(PlayerStates.DASH);
    }
    public void setToSkillOne() {
        onSkill = true;
        setCurrentStates(PlayerStates.SKILL_ONE);
    }

    public void drawSkill(Canvas c) {
        if (currentStates == PlayerStates.SKILL_ONE) {
            playerCharStrategy.drawSkillOne(c);
        }

    }



    public boolean isOnSkill() {
        return onSkill;
    }
    public void backToIdleState() {
        resetAnimation();
        isMakingDamage = false;
        ableMakeDamage = false;
        attacking = false;
        onSkill = false;
        currentStates = PlayerStates.IDLE;
        invincible = false;
    }

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public void setMakingDamage(boolean makingDamage) {
        this.isMakingDamage = makingDamage;
    }

    public boolean isMakingDamage() {
        return isMakingDamage;
    }

    public float getBaseSpeed() {
        return baseSpeed;
    }
    public float getCurrentSpeed() {
        return playerCharStrategy.getCurrentSpeed(baseSpeed, currentStates);
    }

    public void setAbleProjectile(boolean ableProjectile) {
        this.ableProjectile = ableProjectile;
    }

    public boolean isAbleProjectile() {
        return ableProjectile;
    }

    public boolean isAbleMakeDamage() {
        return ableMakeDamage;
    }

    public void setAbleMakeDamage(boolean ableMakeDamage) {
        this.ableMakeDamage = ableMakeDamage;
    }

    public PointF getMoveDelta(double delta, float xSpeed, float ySpeed) {
        return playerCharStrategy.getMoveDelta(delta, xSpeed, ySpeed);
    }

    public int getCurrentDamage() {
        return playerCharStrategy.getCurrentDamage(currentStates, baseDamage);
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void projectileHitEnemy(Projectile p) {
        playerCharStrategy.projectileHitEnemy(p);
    }
    public int getProjectileMaxHit() {
        return playerCharStrategy.getProjectileMaxHit(currentStates);
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setInvincibleTrue() {
        if (!invincible) {
            this.invincible = true;
        }
    }

    public boolean isHaveInteract() {
        return haveInteract;
    }

    public void setHaveInteract(boolean haveInteract) {
        this.haveInteract = haveInteract;
    }

    public boolean isMadeInteraction() {
        return madeInteraction;
    }

    public void setMadeInteraction(boolean madeInteraction) {
        this.madeInteraction = madeInteraction;
    }

    public void increaseHealth(int amount) {
        int newHealth = currentHealth + amount;
        currentHealth = Math.min(newHealth, startingHealth);
    }
    public void increaseBaseDamage() {
        int newDamage = (int) (baseDamage * 1.1);
        baseDamage = Math.min(newDamage, 100);

    }
    public void increaseSpeed() {
        int newDamage = (int) (baseSpeed * 1.1);
        baseSpeed = Math.min(newDamage, 2000);

    }
    public void increaseDefence(int amount) {
        defence += amount;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public double getPercentHealth() {
        return ((double) currentHealth / (double) startingHealth) * 100.0;
    }

    public boolean keepChangeOfDirDuringMovement() {
        return currentStates == PlayerStates.HURT;
    }

    public void setOverlapEnemy(boolean overlapEnemy) {
        if (overlapEnemy && !(this.overlapEnemy)) {
            overlapDir = faceDir;
        }
        this.overlapEnemy = overlapEnemy;

    }

    public boolean ableMoveWhenOverlap() {
        if (overlapEnemy && faceDir == overlapDir) {
            return !(currentStates == PlayerStates.WALK || currentStates == PlayerStates.RUNNING);
        }
        return true;
    }
}