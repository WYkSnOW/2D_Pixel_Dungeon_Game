package com.example.myapplication.ViewModel;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.Model.helper.HelpMethods;

public class MainViewModel extends ViewModel  {

    private static Context gameContext;
    private static int gameWidth;
    private static int gameHeight;
    private static double scaleRatio;

    public void initialize(Context context) {
        gameContext = context;

        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getRealMetrics(dm);

        gameWidth = dm.widthPixels;
        gameHeight = dm.heightPixels;

        double ratioX = gameWidth / 1920.0;
        double ratioY = gameHeight / 1080.0;
        scaleRatio = Math.max(ratioX, ratioY);

        HelpMethods.cleanUi((Activity) context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ((Activity) context).getWindow().getAttributes().layoutInDisplayCutoutMode
                    = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
    }

    public static Context getGameContext() {
        return gameContext;
    }
    public static int getGameWidth() {
        return gameWidth;
    }
    public static int getGameHeight() {
        return gameHeight;
    }
    public static double getScaleRatio() {
        return scaleRatio;
    }
}