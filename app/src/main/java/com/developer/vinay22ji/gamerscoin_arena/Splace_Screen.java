package com.developer.vinay22ji.gamerscoin_arena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class Splace_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace__screen);
        MainActivity.setupMode(this.getWindow(), Splace_Screen.this);
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            startActivity(new Intent(Splace_Screen.this,Login_Activity.class));
        }
        else
        {
            startActivity(new Intent(Splace_Screen.this,MainActivity.class));
        }

    }
}