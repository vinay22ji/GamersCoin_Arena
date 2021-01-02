package com.developer.vinay22ji.gamerscoin_arena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.developer.vinay22ji.gamerscoin_arena.Fragments.Fragment_Home;
import com.developer.vinay22ji.gamerscoin_arena.Fragments.Fragment_Profile;
import com.developer.vinay22ji.gamerscoin_arena.Fragments.Fragment_Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MainActivity extends AppCompatActivity {

    public static final int MODE_DARK = 0;
    public static final int MODE_LIGHT = 1;
    public static boolean  isdarkmode;
    SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(new DarkModePrefManager(MainActivity.this).isNightMode()){
            MainActivity.darkmode(MainActivity.this);
        }
        MainActivity.setupMode(this.getWindow(), MainActivity.this);

        setupTabs(savedInstanceState);

    }

    //we need the outState to save the position
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    public  void setupTabs(Bundle savedInstanceState)
    {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_Task());
        fragmentList.add(new Fragment_Home());
        fragmentList.add(new Fragment_Profile());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        //we need the savedInstanceState to get the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(),
                fragmentList, savedInstanceState);

    }

    public static void darkmode(Context context) {
        DarkModePrefManager darkModePrefManager = new DarkModePrefManager(context);
        darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public static void setupMode(Window window , Context context){
        if(new DarkModePrefManager(context).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            changeStatusBar(MODE_LIGHT,window,context);
            isdarkmode=true;
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            changeStatusBar(MODE_DARK,window,context);
            isdarkmode=false;
        }
    }

    public static void changeStatusBar(int mode, Window window,Context context){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.darkWhite));
            //Light mode
            if(mode==MODE_LIGHT){
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.setStatusBarColor(context.getResources().getColor(R.color.darkWhite));
            }
        }
    }

}