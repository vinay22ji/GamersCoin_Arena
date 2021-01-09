package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.R;

public class Prizes_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prizes_);

        if(new DarkModePrefManager(Prizes_Activity.this).isNightMode()){
            MainActivity.darkmode(Prizes_Activity.this);
        }
        MainActivity.setupMode(this.getWindow(), Prizes_Activity.this);

    }
}