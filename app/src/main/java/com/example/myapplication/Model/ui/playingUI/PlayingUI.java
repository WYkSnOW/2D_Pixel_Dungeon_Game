package com.example.myapplication.Model.ui.playingUI;

import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_HEIGHT;
import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.example.myapplication.Model.entities.GameCharacters;
import com.example.myapplication.Model.entities.Player.Player;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharOne;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharThree;
import com.example.myapplication.Model.entities.Player.playerStartegy.CharTwo;
import com.example.myapplication.Model.entities.Player.playerStates.PlayerStates;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.View.main.gameStates.Playing;

public class PlayingUI {

    //For UI

    //x和y是圆中心位置，可调整. radius即半径

    /*
    private final PointF joystickCenterPos = new PointF(320, GAME_HEIGHT - 250);
    private final PointF attackBtnCenterPos = new PointF(GAME_WIDTH - 320, GAME_HEIGHT - 250);
    private final PointF projectBtnCenterPos = new PointF(GAME_WIDTH - 200, GAME_HEIGHT - 450);
    private final PointF atkModBtnCenterPos = new PointF(GAME_WIDTH - 100, GAME_HEIGHT - 350);
    private final PointF skillOneBtnCenterPos = new PointF(GAME_WIDTH - 480, GAME_HEIGHT - 400); */


    private final PointF joystickCenterPos = new PointF(320, GAME_HEIGHT - 250);
    private PointF innerJoystickCenterPos = joystickCenterPos;
    private final PointF attackBtnCenterPos = new PointF(GAME_WIDTH - 320, GAME_HEIGHT - 250);

    private final PointF atkModBtnCenterPos =  new PointF(GAME_WIDTH - 100, GAME_HEIGHT - 350);
    private final PointF skillOneBtnCenterPos = new PointF(GAME_WIDTH - 350, GAME_HEIGHT - 480);
    private final PointF dashBtnCenterPos = new PointF(GAME_WIDTH - 200, GAME_HEIGHT - 450);
    private final PointF interactBtnCenterPos = new PointF(GAME_WIDTH - 480, GAME_HEIGHT - 400);

    private final PointF runLockBtnCenterPos = new PointF(200, GAME_HEIGHT - 450);



    private final int radius = 150;
    private final int smallRadius = 60;
    private final Paint circlePaint = new Paint();
    private final Paint circlePaint2 = new Paint();
    private final Paint circlePaint3 = new Paint();




    //For Multitouch
    private int joystickPointerId = -1;
    private int attackBtnPointerId = -1;
    private int runLockBtnPointerId = -1;
    private int atkModBtnPointerId = -1;
    private int dashBtnPointerId = -1;
    private int skillOneBtnPointerId = -1;
    private int interactBtnPointerId = -1;


    private boolean touchDown;


    private CustomButton btnMenu;
    private CustomButton btnPause;
    private CustomButton btnBook;
    private final Playing playing;


    private int runLockState = 1;
    private int atkModState = 1;




    public PlayingUI(Playing playing) {
        this.playing = playing;
        circlePaint.setColor(Color.GRAY);
        //circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAlpha(100);
        circlePaint.setStrokeWidth(5);

        circlePaint2.setColor(Color.YELLOW);
        circlePaint2.setAlpha(100);
        circlePaint2.setStrokeWidth(5);

        circlePaint3.setColor(Color.BLUE);
        circlePaint3.setAlpha(100);
        circlePaint3.setStrokeWidth(5);


        btnMenu = new CustomButton(
                5,
                100,
                ButtonImage.PLAYING_MENU.getWidth(),
                ButtonImage.PLAYING_MENU.getHeight()
        );

        btnPause = new CustomButton(
                GAME_WIDTH - ButtonImage.PLAYING_PAUSE.getWidth() - 5,
                5,
                ButtonImage.PLAYING_PAUSE.getWidth(),
                ButtonImage.PLAYING_PAUSE.getHeight()
        );

        btnBook = new CustomButton(
                GAME_WIDTH - (ButtonImage.PLAYING_BOOK.getWidth() * 2) - (5 * 2),
                5,
                ButtonImage.PLAYING_BOOK.getWidth(),
                ButtonImage.PLAYING_BOOK.getHeight()
        );


    }



    public void drawUI(Canvas c) {
        drawCircle(c);
        drawBtn(c);


        //if(touchDown) { //初始点击点在圆环内且并未松开鼠标触发，松开光标被刷新掉
        //    c.drawLine(xCenter, yCenter, xTouch, yTouch, yellowPaint); //画出光标与圆形的三角形
        //    c.drawLine(xCenter, yCenter, xTouch, yCenter, yellowPaint);
        //    c.drawLine(xTouch, yTouch, xTouch, yCenter, yellowPaint);
        //}
    }


    private void drawBtn(Canvas c) {
        c.drawBitmap(
                ButtonImage.PLAYING_MENU.getBtnImg(btnMenu.isPushed(btnMenu.getPointerId())),
                btnMenu.getHitbox().left,
                btnMenu.getHitbox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.PLAYING_PAUSE.getBtnImg(btnPause.isPushed(btnPause.getPointerId())),
                btnPause.getHitbox().left,
                btnPause.getHitbox().top,
                null
        );

        c.drawBitmap(
                ButtonImage.PLAYING_BOOK.getBtnImg(btnBook.isPushed(btnBook.getPointerId())),
                btnBook.getHitbox().left,
                btnBook.getHitbox().top,
                null
        );
    }



    private void drawCircle(Canvas c) {
        c.drawCircle(joystickCenterPos.x, joystickCenterPos.y,
                radius, circlePaint);

        c.drawCircle(attackBtnCenterPos.x, attackBtnCenterPos.y,
                radius, circlePaint);

        c.drawCircle(dashBtnCenterPos.x, dashBtnCenterPos.y,
                smallRadius, circlePaint);

        c.drawCircle(skillOneBtnCenterPos.x, skillOneBtnCenterPos.y,
                smallRadius, circlePaint);

        c.drawCircle(innerJoystickCenterPos.x, innerJoystickCenterPos.y,
                smallRadius, circlePaint);


        if (Player.getInstance().isHaveInteract()) {
            c.drawCircle(interactBtnCenterPos.x, interactBtnCenterPos.y,
                    smallRadius, circlePaint2);
        }



        Paint paint = circlePaint;
        if (runLockState == 2) {
            paint = circlePaint2;
        } else if (runLockState == 3) {
            paint = circlePaint3;
        }
        c.drawCircle(runLockBtnCenterPos.x, runLockBtnCenterPos.y,
                smallRadius, paint);


        if (atkModState == 2) {
            paint = circlePaint2;
        } else {
            paint = circlePaint;
        }
        c.drawCircle(atkModBtnCenterPos.x, atkModBtnCenterPos.y,
                smallRadius, paint);
    }



    private boolean isInsideRadius(PointF eventPos, PointF circle) {
        float a = Math.abs(eventPos.x - circle.x); //找到点击坐标与触控圈圆心的距离
        float b = Math.abs(eventPos.y - circle.y);
        float c = (float) Math.hypot(a, b); //a与b的斜线

        return c <= radius; //光标在圆环内部
    }
    private boolean isInsideSmallRadius(PointF eventPos, PointF circle) {
        float a = Math.abs(eventPos.x - circle.x); //找到点击坐标与触控圈圆心的距离
        float b = Math.abs(eventPos.y - circle.y);
        float c = (float) Math.hypot(a, b); //a与b的斜线

        return c <= smallRadius; //光标在圆环内部
    }

    private boolean checkInsideJoyStick(PointF eventPos, int pointerId) {
        if (isInsideRadius(eventPos, joystickCenterPos)) {
            touchDown = true; //初始点击点在圆环内部
            joystickPointerId = pointerId;
            return true;
        }
        return false;
    }
    private boolean checkInsideAttackBtn(PointF eventPos) {
        return isInsideRadius(eventPos, attackBtnCenterPos);
    }
    private boolean checkInsideRunLock(PointF eventPos) {
        return isInsideSmallRadius(eventPos, runLockBtnCenterPos);
    }
    private boolean checkInsideAtkMod(PointF eventPos) {
        return isInsideSmallRadius(eventPos, atkModBtnCenterPos);

    }
    private boolean checkInsideDashBtn(PointF eventPos) {
        return isInsideSmallRadius(eventPos, dashBtnCenterPos);

    }
    private boolean checkInsideSkillOneBtn(PointF eventPos) {
        return isInsideSmallRadius(eventPos, skillOneBtnCenterPos);

    }
    private boolean checkInsideInteractBtn(PointF eventPos) {
        return isInsideSmallRadius(eventPos, interactBtnCenterPos);

    }

    public void touchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);
        final PointF eventPos = new PointF(event.getX(actionIndex), event.getY(actionIndex));

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {

            actionDown(pointerId, eventPos);

        } else if (action == MotionEvent.ACTION_MOVE) { //点击后移动光标

            actionMove(event);

        } else if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_POINTER_UP) { //松开光标

            actionUp(pointerId, eventPos);
        }

    }

    private void actionDown(int pointerId, PointF eventPos) {
        if (checkInsideJoyStick(eventPos, pointerId)) { //光标在圆环内部

            innerJoystickCenterPos = eventPos; //更新内部摇杆小球
            touchDown = true; //初始点击点在圆环内部
        } else if (checkInsideAttackBtn(eventPos)) {
            if (attackBtnPointerId < 0) {


                if (atkModState == 1) {
                    Player.getInstance().setAttacking(true);
                } else {
                    Player.getInstance().setProjecting(true);
                }


                attackBtnPointerId = pointerId;
            }
        } else if (checkInsideRunLock(eventPos)) {
            if (runLockBtnPointerId < 0) {

                runLockBtnPointerId = pointerId;
            }
        } else if (checkInsideAtkMod(eventPos)) {
            if (atkModBtnPointerId < 0) {
                atkModBtnPointerId = pointerId;
            }
        } else if (checkInsideDashBtn(eventPos)) {
            if (dashBtnPointerId < 0) {
                dashBtnPointerId = pointerId;
            }
        } else if (checkInsideSkillOneBtn(eventPos)) {
            if (skillOneBtnPointerId < 0) {
                skillOneBtnPointerId = pointerId;
            }
        } else if (Player.getInstance().isHaveInteract() && checkInsideInteractBtn(eventPos)) {
            if (interactBtnPointerId < 0) {
                interactBtnPointerId = pointerId;
            }
        } else {
            if (isIn(eventPos, btnMenu)) {
                btnMenu.setPushed(true, pointerId);
            }
            if (isIn(eventPos, btnPause)) {
                btnPause.setPushed(true, pointerId);
            }
            if (isIn(eventPos, btnBook)) {
                btnBook.setPushed(true, pointerId);
            }
            //game.setCurrentGameState(Game.GameState.END);
        }
    }



    private void actionMove(MotionEvent event) {
        if (touchDown) { //只有点击圆环内部而后滑出可以操纵

            for (int i = 0; i < event.getPointerCount(); i++) {
                if (event.getPointerId(i) == joystickPointerId) {
                    //负数意味点击点x值在圆心x值左边（小于），即角色应该左移，反之右移
                    float xDiff = event.getX(i) - joystickCenterPos.x;
                    float yDiff = event.getY(i) - joystickCenterPos.y;


                    float xPos = joystickCenterPos.x + returnDiff(xDiff); //更新摇杆内部小球
                    float yPos = joystickCenterPos.y + returnDiff(yDiff);
                    innerJoystickCenterPos = new PointF(xPos, yPos);



                    //传输进入控制板中决定玩家是否移动的function
                    if (!(Player.getInstance().isAttacking()
                            || Player.getInstance().isOnSkill()
                            || Player.getInstance().isProjecting())) {
                        playing.setPlayerMoveTrue(new PointF(xDiff, yDiff));
                        if (runLockState == 2) {
                            Player.getInstance().setCurrentStates(PlayerStates.RUNNING);
                        } else if (runLockState == 3) {
                            Player.getInstance().setCurrentStates(PlayerStates.WALK);
                        } else {
                            if (checkInsideJoyStick(
                                    new PointF(event.getX(),
                                            event.getY()),
                                    event.getPointerId(i))) {
                                Player.getInstance().setCurrentStates(PlayerStates.WALK);


                            } else {
                                Player.getInstance().setCurrentStates(PlayerStates.RUNNING);
                            }
                        }
                    }


                }
            }

        }
    }



    private void actionUp(int pointerId, PointF eventPos) {
        if (pointerId == joystickPointerId) {
            resetJoystickButton();


        } else {
            if (isIn(eventPos, btnMenu)) {
                if (btnMenu.isPushed(pointerId)) {
                    resetJoystickButton();

                    //Player.getInstance().setWinTheGame(true);
                    //playing.setGameStateToEnd();

                    if (Player.getInstance().getGameCharType() == GameCharacters.CENTAUR) {
                        Player.getInstance().setCharStrategy(new CharTwo());
                    } else if (Player.getInstance().getGameCharType()
                            == GameCharacters.WITCH2) {
                        Player.getInstance().setCharStrategy(new CharThree());
                    } else {
                        Player.getInstance().setCharStrategy(new CharOne());
                    }

                }
            } else if (isIn(eventPos, btnPause)) {
                if (btnPause.isPushed(pointerId)) {
                    resetJoystickButton();
                    playing.changeOnPause();
                }
            } else if (isIn(eventPos, btnBook)) {
                if (btnBook.isPushed(pointerId)) {
                    resetJoystickButton();
                    playing.changeOnBook();
                }
            }
        }


        //btnConfig.unPush(pointerId);

        btnUpAction(pointerId);
    }






    private float returnDiff(float diff) {
        if (diff < 0) {
            return Math.max(-(2 * smallRadius), diff);
        } else {
            return Math.min((2 * smallRadius), diff);
        }
    }



    private void btnUnPush(int pointerId) {
        btnMenu.unPush(pointerId);
        btnPause.unPush(pointerId);
        btnBook.unPush(pointerId);
    }


    private void btnUpAction(int pointerId) {
        btnUnPush(pointerId);

        if (pointerId == attackBtnPointerId) {

            if (atkModState == 1) {
                Player.getInstance().setAttacking(false);
            } else {
                Player.getInstance().setProjecting(false);
            }

            attackBtnPointerId = -1;
        }

        if (pointerId == runLockBtnPointerId) {
            if (runLockState < 3) {
                runLockState += 1;
            } else {
                runLockState = 1;
            }
            runLockBtnPointerId = -1;
        }


        if (pointerId == atkModBtnPointerId) {

            if (atkModState < 2) {
                atkModState += 1;
            } else {
                atkModState = 1;
            }
            Player.getInstance().setAttacking(false);
            Player.getInstance().setProjecting(false);
            atkModBtnPointerId = -1;
        }

        if (pointerId == dashBtnPointerId) {
            if (!(Player.getInstance().isOnSkill())) {
                Player.getInstance().setToDash();
            }
            dashBtnPointerId = -1;
        }
        if (pointerId == skillOneBtnPointerId) {
            if (!(Player.getInstance().isOnSkill())) {
                Player.getInstance().setToSkillOne();
            }
            skillOneBtnPointerId = -1;
        }

        if (pointerId == interactBtnPointerId) {
            Player.getInstance().setMadeInteraction(true);
            interactBtnPointerId = -1;
        }


    }










    private void resetJoystickButton() {
        touchDown = false; //松开光标即操作消失
        innerJoystickCenterPos = joystickCenterPos;
        playing.setPlayerMoveFalse();
        joystickPointerId = -1;
    }

    private boolean isIn(PointF eventPos, CustomButton b) {
        return b.getHitbox().contains(eventPos.x, eventPos.y);
    }
}