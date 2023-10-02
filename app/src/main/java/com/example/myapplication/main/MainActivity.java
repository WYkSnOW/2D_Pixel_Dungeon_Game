package com.example.myapplication.main;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static Context gameContext;
    public static int GAME_WIDTH, GAME_HEIGHT;
    private static double scaleRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);

        GAME_WIDTH = dm.widthPixels;
        GAME_HEIGHT = dm.heightPixels;


        double ratioX = MainActivity.GAME_WIDTH / 1920.0;
        double ratioY = MainActivity.GAME_HEIGHT / 1080.0;
        scaleRatio = Math.max(ratioX, ratioY);


        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }




        setContentView(new GamePanel(this));

    }

    public static Context getGameContext() {
        return gameContext;
    }

    public static double getScaleRatio() {
        return scaleRatio;
    }
}