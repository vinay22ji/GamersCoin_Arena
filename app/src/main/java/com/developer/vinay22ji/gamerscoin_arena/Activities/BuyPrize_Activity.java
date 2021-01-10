package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.R;

public class BuyPrize_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_prize_);

        if(new DarkModePrefManager(BuyPrize_Activity.this).isNightMode()){
            MainActivity.darkmode(BuyPrize_Activity.this);
        }else {

        }
        MainActivity.setupMode(this.getWindow(), BuyPrize_Activity.this);

        findViewById(R.id.buyActivityback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}