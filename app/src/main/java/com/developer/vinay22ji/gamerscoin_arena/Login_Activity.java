package com.developer.vinay22ji.gamerscoin_arena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import static java.security.AccessController.getContext;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
//
//        if(new DarkModePrefManager(Login_Activity.this).isNightMode()){
//            MainActivity.darkmode(Login_Activity.this);
//        }else {
//
//        }

        MainActivity.setupMode(this.getWindow(), Login_Activity.this);

    }
}