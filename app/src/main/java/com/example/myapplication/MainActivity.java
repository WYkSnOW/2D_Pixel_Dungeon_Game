package com.example.myapplication;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private  int count = 0;
    private boolean isZombieFlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        VideoView backVideo = (VideoView) findViewById(R.id.congifBackground);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.congif_background;
        backVideo.setVideoURI(Uri.parse(uri));
        backVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start(); //
            }
        });



        ImageView zombie = findViewById(R.id.runningCharacter);
        AnimationDrawable runningZombie = (AnimationDrawable) zombie.getBackground();
        runningZombie.start(); //跑步动画开始

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isZombieFlip) {
                    zombie.setScaleX(1f);
                    isZombieFlip = false;
                } else {
                    zombie.setScaleX(-1f);
                    isZombieFlip = true;
                }
            }
        }, 10000, 10000);


        /*
        // 创建一个沿着X轴移动的属性动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(zombie, "translationX", 0f, 2000f); // 从0移动到500像素
        animator.setDuration(3000); // 设置动画持续时间(animation time in millisecond)
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) { // 在动画结束后执行你的操作(action after animation end)
                super.onAnimationEnd(animation);
                // 创建反向动画
                ObjectAnimator reverseAnimator = ObjectAnimator.ofFloat(zombie, "translationX", 2000f, 0f);
                reverseAnimator.setDuration(3000);

                reverseAnimator.start(); // 启动反向动画
            }
        });
        animator.start(); // 启动属性动画*/

        ObjectAnimator moveForward = ObjectAnimator.ofFloat(zombie, "translationX", 0f, 2000f);
        moveForward.setDuration(10000); // 设置动画持续时间

        // 创建一个沿着X轴移动回初始点的属性动画
        ObjectAnimator moveBackward = ObjectAnimator.ofFloat(zombie, "translationX", 2000f, 0f);
        moveBackward.setDuration(10000); // 设置动画持续时间

        // 创建一个动画集合，包含先正向移动再反向移动的两个动画
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(moveForward, moveBackward);

        // 添加监听器，当动画结束时重新启动动画
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { }
            @Override
            public void onAnimationEnd(Animator animation) {
                // 重新启动动画
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) { }
            @Override
            public void onAnimationRepeat(@NonNull Animator animator) { }
        });
        animatorSet.start(); // 启动属性动画

        Timer timer = new Timer(true);





        Button button = findViewById(R.id.goes_to_congifScreen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ConfigScreen.class);
                startActivity(intent);
                finish();
            }
        });


        Button exitBtn = findViewById(R.id.Exit);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
