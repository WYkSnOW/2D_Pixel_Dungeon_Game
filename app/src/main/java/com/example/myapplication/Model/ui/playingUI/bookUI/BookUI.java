package com.example.myapplication.Model.ui.playingUI.bookUI;



import static com.example.myapplication.Model.helper.GameConstants.UiSize.GAME_WIDTH;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;


import com.example.myapplication.Model.leaderBoard.Leaderboard;
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


    private boolean flippingLeft = false;
    private boolean flippingRight = false;
    private int categoriesState = 0;
    private final int categoriesBtnAmount = 6;


    private final PointF bookPos;
    private final PointF bookLeftTop;
    private final PointF firstBtnPos;
    private final PointF categoriesBtnSize;
    private final float btnSpace;


    private final Paint paint = new Paint();

    public BookUI(Playing playing) {
        this.playing = playing;
        this.bookPos = new PointF(
                (float) ((GAME_WIDTH / 2) - (GameVideos.BOOK_OPENING.getWidth() / 2)),
                -(float) (GameVideos.BOOK_OPENING.getHeight() / 5)
        );

        this.bookLeftTop = new PointF(
                bookPos.x + (float) (145 * GameVideos.BOOK_OPENING.getScale()),
                bookPos.y + (float) (160 * GameVideos.BOOK_OPENING.getScale())
        );

        this.firstBtnPos = new PointF(
                bookLeftTop.x + (float) (482 * GameVideos.BOOK_OPENING.getScale()),
                bookLeftTop.y + (float) (46 * GameVideos.BOOK_OPENING.getScale())
        );

        this.categoriesBtnSize = new PointF(
                (float) (30 * GameVideos.BOOK_OPENING.getScale()),
                (float) (26 * GameVideos.BOOK_OPENING.getScale())
        );

        this.btnSpace = (float) (39 * GameVideos.BOOK_OPENING.getScale());


        btnResume = new CustomButton(
                GAME_WIDTH - ButtonImage.PLAYING_RESUME.getWidth() - 5,
                5,
                ButtonImage.PLAYING_RESUME.getWidth(),
                ButtonImage.PLAYING_RESUME.getHeight()
        );



        paint.setColor(Color.RED);
        paint.setAlpha(100);
        paint.setStrokeWidth(5);
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


                    if (flippingLeft) {
                        drawBookFlipLeft(c);
                    } else if (flippingRight) {
                        drawBookFlipRight(c);
                    } else {

                        ableClick = true;
                        drawBookCategories(c);

                        if (categoriesState == 3) {
                            Leaderboard.getInstance().drawLeaderBoardInGame(
                                            c, bookLeftTop,
                                            (float) GameVideos.BOOK_OPENING.getScale()
                            );
                        }
                        //WordsResource.WORDS.drawWords(c);

                    }


                }
            }
        }


    }


    private void drawBookFlipLeft(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_LEFT_FLIP.getSprite(0,
                        aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        updateAnimation(GameVideos.BOOK_LEFT_FLIP.getAnimRate());

        if (aniIndex >= GameVideos.BOOK_LEFT_FLIP.getMaxAnimIndex()) {
            aniIndex = 0;
            flippingLeft = false;
            ableClick = false;
        }
    }

    private void drawBookFlipRight(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_RIGHT_FLIP.getSprite(0,
                        aniIndex),
                bookPos.x,
                bookPos.y,
                null
        );
        updateAnimation(GameVideos.BOOK_RIGHT_FLIP.getAnimRate());

        if (aniIndex >= GameVideos.BOOK_RIGHT_FLIP.getMaxAnimIndex()) {
            aniIndex = 0;
            flippingRight = false;
            ableClick = false;
        }
    }


    private void drawBookCategories(Canvas c) {
        c.drawBitmap(
                GameVideos.BOOK_CATEGORIES.getSprite(0,
                        categoriesState),
                bookPos.x,
                bookPos.y,
                null
        );

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

            categoriesState = 0;

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


            isInCategories(eventPos);


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

    private void isInCategories(PointF eventPos) {
        for (int i = 0; i < categoriesBtnAmount; i++) {
            RectF btn = new RectF(
                    firstBtnPos.x,
                    firstBtnPos.y + (btnSpace * i),
                    firstBtnPos.x + categoriesBtnSize.x,
                    firstBtnPos.y + (btnSpace * i) + categoriesBtnSize.y
            );

            if (btn.contains(eventPos.x, eventPos.y)) {
                if (i != categoriesState) {
                    if (i < categoriesState) {
                        flippingLeft = true;
                    } else {
                        flippingRight = true;
                    }
                    ableClick = false;
                    categoriesState = i;
                }

            }
        }
    }








}
