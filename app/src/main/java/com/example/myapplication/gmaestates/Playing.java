package com.example.myapplication.gmaestates;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.example.myapplication.entities.Character;
import com.example.myapplication.entities.Player;
import com.example.myapplication.entities.enemies.Zombie;
import com.example.myapplication.environments.MapManager;
import com.example.myapplication.helper.GameConstants;
import com.example.myapplication.helper.interfaces.GameStateInterFace;
import com.example.myapplication.main.Game;

import java.util.Random;

public abstract class Playing extends BaseState implements GameStateInterFace {
    private float cameraX, cameraY;
    private boolean movePlayer;
    private PointF lastTouchDiff;
    private MapManager mapManager;
    private Player player;
    private Zombie zombie;


    //private ArrayList<RndSquare> squares = new ArrayList<>();
    private Random rand = new Random();


    //For touch circle
    private float xCenter = 250, yCenter = 800, radius = 150; //x和y是圆中心位置，可调整. radius即半径
    private Paint circlePaint;

    private float xTouch, yTouch;
    private boolean touchDown;


    public Playing(Game game) {
        super(game);
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        //mob1Pos = new PointF(rand.nextInt(GAME_WIDTH), rand.nextInt(GAME_HEIGHT)); //随机初始位置（在屏幕范围内）

        mapManager = new MapManager();
        player = new Player();
        zombie = new Zombie(new PointF(100, 100));
    }

    @Override
    public void update(double delta) {
        updatePlayerMove(delta);
        player.update(delta, movePlayer);
        zombie.update(delta);
        mapManager.setCameraValues(cameraX, cameraY);
    }

    @Override
    public void render(Canvas c) {
        mapManager.draw(c);

        drawUI(c); //Touch circle

        drawPlayer(c);
        drawCharacter(c,zombie);
    }

    private void drawUI(Canvas c) {
        c.drawCircle(xCenter, yCenter, radius, circlePaint);

        //if(touchDown) { //初始点击点在圆环内且并未松开鼠标触发，松开光标被刷新掉
        //    c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint); //画出光标与圆形的三角形
        //    c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
        //    c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        //}
    }

    private void drawPlayer(Canvas c) {

        c.drawBitmap(
                player.getGameCharType().getSprite(player.getDrawDir(), player.getAniIndex()), //在本游戏中，行数Y是不同形态，而列数X是该姿势中的不同帧。根据不同输入需要调换，其他怪物同理
                player.getHitBox().left, player.getHitBox().top, null
        );

    }

    public void drawCharacter(Canvas canvas, Character character) {
        canvas.drawBitmap(
                character.getGameCharType().getSprite(character.getDrawDir(), character.getAniIndex()),
                character.getHitBox().left + cameraX, character.getHitBox().top + cameraY, null
        );
    }

    private void updatePlayerMove(double delta) {
        if (!movePlayer)
            return;

        float baseSpeed = (float) delta * 300;
        float ratio = Math.abs(lastTouchDiff.y) / Math.abs(lastTouchDiff.x);
        double angle = Math.atan(ratio); //找到玩家滑动的角度

        float xSpeed = (float) Math.cos(angle); //用角度与直线速度计算斜向速度
        float ySpeed = (float) Math.sin(angle);



        if(xSpeed > ySpeed) { //意味着x角度更大，角色应该随着x调整     实现角色移动时的动画切换
            if (lastTouchDiff.x > 0) { //正数意味光标在圆环右侧，即朝右移动
                player.setFaceDir(GameConstants.Face_Dir.RIGHT);
                player.setDrawDir(GameConstants.Face_Dir.RIGHT);
            } else if (lastTouchDiff.x < 0) {
                player.setFaceDir(GameConstants.Face_Dir.LEFT);
                player.setDrawDir(GameConstants.Face_Dir.LEFT);
            }
        } else {
            if (lastTouchDiff.y > 0) {
                player.setFaceDir(GameConstants.Face_Dir.UP);
            } else if (lastTouchDiff.y < 0) {
                player.setFaceDir(GameConstants.Face_Dir.DOWN);
            }
        }


        if(lastTouchDiff.x < 0) {
            xSpeed *= -1;
        }
        if(lastTouchDiff.y < 0) {
            ySpeed *= -1;
        }

        int pWidth = GameConstants.Sprite.SIZE; //计算player的实际碰撞体积
        int pHeight = GameConstants.Sprite.SIZE;
        if (xSpeed <= 0) //当角色往左边或上边移动时，判定点为左上角，则将碰撞修正设置为0
            pWidth = 0;
        if (ySpeed <= 0)
            pHeight = 0;

        float deltaX = xSpeed * baseSpeed * -1; //移动镜头而不是角色
        float deltaY = ySpeed * baseSpeed * -1; //因镜头需与角色相反的方向移动，即乘以-1

        if(mapManager.canMoveHere(player.getHitBox().left + cameraX * -1 + deltaX * -1 + pWidth, player.getHitBox().top + cameraY * -1 + deltaY * -1 + pHeight)) { //查看是否允许通过，是则增加camera值准许移动
            cameraX += deltaX;
            cameraY += deltaY;
        }

    }

    public void setPlayerMoveTrue(PointF lastTouchDiff) { //查看是否应该移动角色（触发移动后没有松开光标）
        movePlayer = true;
        this.lastTouchDiff = lastTouchDiff;

    }

    public void setPlayerMoveFalse() {
        movePlayer = false; //在操作板class中，松开光标/键盘后将角色移动设置为false，即停止角色移动
        player.resetAnimation();
    }

    @Override
    public void touchEvents(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX(); //找到点击位置的坐标
                float y = event.getY();

                float a = Math.abs(x - xCenter); //找到点击坐标与触控圈圆心的距离
                float b = Math.abs(y - yCenter);
                float c = (float) Math.hypot(a,b); //a与b的斜线

                if(c <= radius) { //光标在圆环内部
                    touchDown = true; //初始点击点在圆环内部
                    xTouch = x;
                    yTouch = y;
                } else {
                    game.setCurrentGameState(Game.GameState.MENU);
                }
                break;


            case MotionEvent.ACTION_MOVE: //点击后移动光标
                if(touchDown) { //只有点击圆环内部而后滑出可以操纵
                    xTouch = event.getX();
                    yTouch = event.getY();

                    float xDiff = xTouch - xCenter; //负数意味点击点x值在圆心x值左边（小于），即角色应该左移，反之右移
                    float yDiff = yTouch - yCenter;

                    setPlayerMoveTrue(new PointF(xDiff, yDiff)); //传输进入控制板中决定玩家是否移动的function
                }
                break;
            case MotionEvent.ACTION_UP: //松开光标
                touchDown = false; //松开光标即操作消失
                setPlayerMoveFalse();
                break;
        }
    }




}
