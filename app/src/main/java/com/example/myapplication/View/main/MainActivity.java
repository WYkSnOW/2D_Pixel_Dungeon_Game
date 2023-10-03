package com.example.myapplication.View.main;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    protected static Context gameContext;
    public static int gameWidth;
    public static int gameHeight;
    private static double scaleRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        gameWidth = dm.widthPixels; //get screen width and height
        gameHeight = dm.heightPixels;


        double ratioX = MainActivity.gameWidth / 1920.0; //ratio use to upscale background videos or image
        double ratioY = MainActivity.gameHeight / 1080.0;
        scaleRatio = Math.max(ratioX, ratioY);


        getWindow().getDecorView().setSystemUiVisibility(//take off ui of the phone
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }


        setContentView(new GamePanel(this)); //lead to Game panel

    }

    public static Context getGameContext() {
        return gameContext;
    }

    public static double getScaleRatio() {
        return scaleRatio;
    }
}