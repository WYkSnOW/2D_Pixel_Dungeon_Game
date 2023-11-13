package com.example.myapplication.Model.ui.playingUI.bookUI;



import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;


import com.example.myapplication.Model.loopVideo.GameVideos;
import com.example.myapplication.Model.ui.ButtonImage;
import com.example.myapplication.Model.ui.CustomButton;
import com.example.myapplication.View.main.gameStates.Playing;

public class BookUI {

    private final Playing playing;
    private CustomButton btnResume;


    private boolean opingBook = true;
    private boolean btnAppearing = true;

    private boolean btnDisappearing = false;
    private boolean closing = false;

    private boolean ableClick = false;

    private int aniIndex = 0;
    private int aniTick = 0;




    private PointF bookPos;

    public BookUI(Playing playing) {
        this.playing = playing;
        this.bookPos = new PointF(
                (float) ((GAME_WIDTH / 2) - (GameVideos.BOOK_OPENING.getWidth() / 2)),
                -(float) (GameVideos.BOOK_OPENING.getHeight() / 5)
        );

        btnResume = new CustomButton(
                GAME_WIDTH - ButtonImage.PLAYING_RESUME.getWidth() - 5,
                5,
                ButtonImage.PLAYING_RESUME.getWidth(),
                ButtonImage.PLAYING_RESUME.getHeight()
        );
    }

    public void drawUI(Canvas c) {
        drawBtn(c);
        drawBook(c);
    }




    private void drawBook(Canvas c) {
        if (opingBook) {
            drawOpenBook(c);
        } else {
            if (btnAppearing) {
                drawBtnAppearing(c);
            } else {
                if (btnDisappearing) {
                    if (closing) {
                        drawClosing(c);
                    } else {
                        drawBtnDisappear(c);
                    }
                } else {
                    ableClick = true;
                    c.drawBitmap(
                            GameVideos.BOOK_BTN_APPEARING.getSprite(0,
                                    GameVideos.BOOK_BTN_APPEARING.getMaxAnimIndex() - 1),
                            bookPos.x,
                            bookPos.y,
                            null
                    );


                }

            }
        }



    }

    private void drawClosing(Canvas c) {

        c.drawBitmap(
                GameVideos.BOOK_CLOSING.getSprite(0, aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        updateAnimation(GameVideos.BOOK_CLOSING.getAnimRate());

        if (aniIndex >= GameVideos.BOOK_CLOSING.getMaxAnimIndex()) {
            aniIndex = 0;

            ableClick = false;

            btnDisappearing = false;
            closing = false;

            opingBook = true;
            btnAppearing = true;
            playing.changeOnBook();
        }


    }


    private void drawBtnDisappear(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_BTN_APPEARING.getSprite(0, aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        blackPlayAnimation(GameVideos.BOOK_BTN_APPEARING.getAnimRate());
        if (aniIndex <= 0) {
            aniIndex = 0;
            closing = true;
        }
    }

    private void drawBtnAppearing(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_BTN_APPEARING.getSprite(0, aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        updateAnimation(GameVideos.BOOK_BTN_APPEARING.getAnimRate());

        if (aniIndex >= GameVideos.BOOK_BTN_APPEARING.getMaxAnimIndex()) {
            aniIndex = 0;
            btnAppearing = false;
        }
    }





    private void drawOpenBook(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_OPENING.getSprite(0, aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        updateAnimation(GameVideos.BOOK_OPENING.getAnimRate());

        if (aniIndex >= GameVideos.BOOK_OPENING.getMaxAnimIndex()) {
            aniIndex = 0;
            opingBook = false;
        }
    }

    private void blackPlayAnimation(int animRate) {
        aniTick++;
        if (aniTick >= animRate) {
            aniTick = 0;
            aniIndex--;
        }
    }

    private void updateAnimation(int animRate) {
        aniTick++;
        if (aniTick >= animRate) {
            aniTick = 0;
            aniIndex++;
        }
    }


    private void drawBtn(Canvas c) {
        c.drawBitmap(
                ButtonImage.PLAYING_RESUME.getBtnImg(btnResume.isPushed(btnResume.getPointerId())),
                btnResume.getHitbox().left,
                btnResume.getHitbox().top,
                null
        );

    }


    public void touchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        final int actionIndex = event.getActionIndex();
        final int pointerId = event.getPointerId(actionIndex);
        final PointF eventPos = new PointF(event.getX(actionIndex), event.getY(actionIndex));


        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {


            if (isIn(eventPos, btnResume)) {
                btnResume.setPushed(true, pointerId);
            }
        } else if (action == MotionEvent.ACTION_UP
                || action == MotionEvent.ACTION_POINTER_UP) { //松开光标
            if (isIn(eventPos, btnResume)) {
                if (btnResume.isPushed(pointerId)) {
                    if (ableClick) {
                        aniIndex = GameVideos.BOOK_BTN_APPEARING.getMaxAnimIndex() - 1;
                        ableClick = false;
                        btnDisappearing = true;
                    }


                }
            }
            btnUpAction(pointerId);
        }

    }



    private void btnUpAction(int pointerId) {
        btnResume.unPush(pointerId);
    }





    private boolean isIn(PointF eventPos, CustomButton b) {
        return b.getHitbox().contains(eventPos.x, eventPos.y);
    }







}
