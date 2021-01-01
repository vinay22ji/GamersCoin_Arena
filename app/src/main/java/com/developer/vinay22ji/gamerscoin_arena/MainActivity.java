package com.developer.vinay22ji.gamerscoin_arena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static final int MODE_DARK = 0;
    public static final int MODE_LIGHT = 1;
    public static boolean  isdarkmode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void darkmode(Context context)
    {
        DarkModePrefManager darkModePrefManager = new DarkModePrefManager(context);
        darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }


    public static void setupMode(Window window , Context context){
        if(new DarkModePrefManager(context).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            changeStatusBar(MODE_DARK,window,context);
            isdarkmode=true;
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            changeStatusBar(MODE_LIGHT,window,context);
            isdarkmode=false;
        }
    }

//    public static void changeStatusBar(int mode, Window window,Context context){
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(context.getResources().getColor(R.color.contentStatusBar));
//            //Light mode
//            if(mode==MODE_LIGHT){
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            }
//        }
//    }

}