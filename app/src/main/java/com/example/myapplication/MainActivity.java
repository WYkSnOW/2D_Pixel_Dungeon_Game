package com.example.myapplication;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private boolean isZombieFlip;
    private static MediaPlayer openBGM;
    private ImageView zombie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //set and start background music
        Uri uriBGM = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.open_bgm);
        openBGM = MediaPlayer.create(this, uriBGM);
        openBGM.setLooping(true);
        openBGM.start();

        //set up background video
        Video mainBackgroundVideo = new Video(this, R.raw.knight_background);
        FrameLayout backgroundVideoContainer = findViewById(R.id.MainBackground);
        backgroundVideoContainer.addView(mainBackgroundVideo);

        //Animation of a running zombie
        zombie = findViewById(R.id.runningCharacter);
        AnimationDrawable runningZombie = (AnimationDrawable) zombie.getBackground();
        runningZombie.start();

        //A timer that fit the character every 10 second(10000ms)
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                flipRunningCharacter();
            }
        }, 10000, 10000);

        //Create animation of moving imageView from 0f to 2000f and 2000f to 0f
        ObjectAnimator moveF = anim(zombie, "translationX", 0f, 2000f); //move from 0f to 2000f in x
        moveF.setDuration(10000); // take 10000ms (10 second to do)
        ObjectAnimator moveB = anim(zombie, "translationX", 2000f, 0f); //move from 2000f to 0f in x
        moveB.setDuration(10000); // take 10000ms (10 second to do)

        // A Animatorset that contain two of the previous animation
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveF, moveB);

        //loop those two animation(replay when it's end)
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.start(); //restart
            }
            @Override
            public void onAnimationCancel(@NonNull Animator animator) { }
            @Override
            public void onAnimationRepeat(@NonNull Animator animator) { }
        });
        animatorSet.start(); //start animation

        //Button that take user to next activity
        Button button = findViewById(R.id.goes_to_congifScreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ConfigScreen.class);
                timer.cancel(); //close timer
                //close and release video
                mainBackgroundVideo.surfaceDestroyed(mainBackgroundVideo.getHolder());
                startActivity(intent);
                finish();
            }
        });

        //Button that close the current activity and left the game.
        Button exitBtn = findViewById(R.id.Exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endMusic();
                finish();
            }
        });
    }


    private void flipRunningCharacter() {
        if (isZombieFlip) {
            zombie.setScaleX(1f);
            isZombieFlip = false;
        } else {
            zombie.setScaleX(-1f);
            isZombieFlip = true;
        }
    }

    //Function that can be call in other class to end the background music
    public static void endMusic() {
        if (openBGM != null && openBGM.isPlaying()) {
            openBGM.stop();
            openBGM.release();
        }
    }
    //create ObjectAnimator
    private ObjectAnimator anim(ImageView imageView, String propertyName, float start, float end) {
        return ObjectAnimator.ofFloat(imageView, propertyName, start, end);
    }

}
