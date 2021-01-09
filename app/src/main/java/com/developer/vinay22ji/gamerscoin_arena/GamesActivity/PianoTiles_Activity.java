package com.developer.vinay22ji.gamerscoin_arena.GamesActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.Activities.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.R;

import java.util.Random;

public class PianoTiles_Activity extends AppCompatActivity {

    ImageView iv_11,iv_12,iv_13,
            iv_21,iv_22,iv_23,
            iv_31,iv_32,iv_33,
            iv_41,iv_42,iv_43,
            iv_51,iv_52,iv_53;

    AppCompatButton b_play;

    TextView tv_time,tv_score,tv_best;

    Random r;

    int rockLockationRow1,rockLockationRow2,rockLockationRow3,rockLockationRow4,rockLockationRow5;

    int frameImage,pawInFrameImage,tapImage,emptyImage;
    int currentScore = 0;
    int bestScore;

    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_tiles_);
        MainActivity.setupMode(this.getWindow(), PianoTiles_Activity.this);
        SharedPreferences preferences=getSharedPreferences("PREFS",0);
        bestScore=preferences.getInt("highscore",0);

        iv_11=findViewById(R.id.tv11);
        iv_12=findViewById(R.id.tv12);
        iv_13=findViewById(R.id.tv13);

        iv_21=findViewById(R.id.tv21);
        iv_22=findViewById(R.id.tv22);
        iv_23=findViewById(R.id.tv23);

        iv_31=findViewById(R.id.tv31);
        iv_32=findViewById(R.id.tv32);
        iv_33=findViewById(R.id.tv33);

        iv_41=findViewById(R.id.tv41);
        iv_42=findViewById(R.id.tv42);
        iv_43=findViewById(R.id.tv43);

        iv_51=findViewById(R.id.tv51);
        iv_52=findViewById(R.id.tv52);
        iv_53=findViewById(R.id.tv53);

        b_play=findViewById(R.id.b_play);

        tv_best= findViewById(R.id.tv_best);
        tv_best.setText("BEST : "+bestScore);
        tv_score=findViewById(R.id.tv_score);
        tv_score.setText("SCORE : "+currentScore);
        tv_time=findViewById(R.id.tv_time);
        tv_time.setText("TIME : "+millisToTime(15000));

        r=new Random();

        loadImages();

        timer=new CountDownTimer(15000,500) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_time.setText("TIME : "+ millisToTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                tv_time.setText("TIME : "+ millisToTime(0));

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);

                b_play.setVisibility(View.VISIBLE);

                iv_11.setImageResource(emptyImage);
                iv_12.setImageResource(emptyImage);
                iv_13.setImageResource(emptyImage);

                iv_21.setImageResource(emptyImage);
                iv_22.setImageResource(emptyImage);
                iv_23.setImageResource(emptyImage);

                iv_31.setImageResource(emptyImage);
                iv_32.setImageResource(emptyImage);
                iv_33.setImageResource(emptyImage);

                iv_41.setImageResource(emptyImage);
                iv_42.setImageResource(emptyImage);
                iv_43.setImageResource(emptyImage);

                iv_51.setImageResource(emptyImage);
                iv_52.setImageResource(emptyImage);
                iv_53.setImageResource(emptyImage);

                Toast.makeText(PianoTiles_Activity.this, "Game Over!", Toast.LENGTH_SHORT).show();

                if(currentScore>bestScore)
                {
                    bestScore=currentScore;
                    tv_best.setText("BEST : "+ bestScore);

                    SharedPreferences preferences1=getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor=preferences1.edit();
                    editor.putInt("highscore",bestScore);
                    editor.apply();

                }

            }
        };

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rockLockationRow3 == 1)
                {
                    continueGame();
                }
                else
                {
                    endGame();
                }

            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rockLockationRow3 == 2)
                {
                    continueGame();
                }
                else
                {
                    endGame();
                }

            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rockLockationRow3 == 3)
                {
                    continueGame();
                }
                else
                {
                    endGame();
                }

            }
        });

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                initGame();

            }
        });


    }

    private void continueGame()
    {
        //row5
        rockLockationRow5 = rockLockationRow4;
        setRockLocation(rockLockationRow5,5);

        //row4
        rockLockationRow4 = rockLockationRow3;
        setRockLocation(rockLockationRow4,4);

        //row3
        rockLockationRow3 = rockLockationRow2;
        setRockLocation(rockLockationRow3,3);

        //row2
        rockLockationRow2 = rockLockationRow1;
        setRockLocation(rockLockationRow2,2);

        //row1
        rockLockationRow1 = r.nextInt(3) + 1;
        setRockLocation(rockLockationRow1,1);

        currentScore++;

        tv_score.setText("SCORE : "+currentScore);
    }

    private void initGame()
    {
        iv_11.setImageResource(emptyImage);
        iv_12.setImageResource(emptyImage);
        iv_13.setImageResource(emptyImage);
        iv_21.setImageResource(emptyImage);
        iv_22.setImageResource(emptyImage);
        iv_23.setImageResource(emptyImage);
        iv_31.setImageResource(emptyImage);
        iv_32.setImageResource(emptyImage);
        iv_33.setImageResource(emptyImage);
        iv_41.setImageResource(emptyImage);
        iv_42.setImageResource(emptyImage);
        iv_43.setImageResource(emptyImage);
        iv_51.setImageResource(emptyImage);
        iv_52.setImageResource(emptyImage);
        iv_53.setImageResource(emptyImage);

        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        b_play.setVisibility(View.INVISIBLE);

        currentScore=0;
        tv_score.setText("SCORE : "+currentScore);

        timer.start();

        //row5-nothing
        //row4
        rockLockationRow4=2;
        iv_42.setImageResource(pawInFrameImage);

        //row3
        rockLockationRow3=2;
        iv_32.setImageResource(tapImage);

        //row2
        rockLockationRow2=r.nextInt(3) + 1;
        setRockLocation(rockLockationRow2,2);

        //row1
        rockLockationRow1=r.nextInt(3) + 1;
        setRockLocation(rockLockationRow1,1);


    }

    private void endGame()
    {
        timer.cancel();

        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);

        b_play.setVisibility(View.VISIBLE);

//        iv_11.setImageResource(emptyImage);
//        iv_12.setImageResource(emptyImage);
//        iv_13.setImageResource(emptyImage);
//
//        iv_21.setImageResource(emptyImage);
//        iv_22.setImageResource(emptyImage);
//        iv_23.setImageResource(emptyImage);
//
//        iv_31.setImageResource(emptyImage);
//        iv_32.setImageResource(emptyImage);
//        iv_33.setImageResource(emptyImage);
//
//        iv_41.setImageResource(emptyImage);
//        iv_42.setImageResource(emptyImage);
//        iv_43.setImageResource(emptyImage);
//
//        iv_51.setImageResource(emptyImage);
//        iv_52.setImageResource(emptyImage);
//        iv_53.setImageResource(emptyImage);

        if(currentScore>bestScore)
        {
            bestScore=currentScore;
            tv_best.setText("BEST : "+ bestScore);

            SharedPreferences preferences1=getSharedPreferences("PREFS",0);
            SharedPreferences.Editor editor=preferences1.edit();
            editor.putInt("highscore",bestScore);
            editor.apply();

        }

        Toast.makeText(PianoTiles_Activity.this, "Game Over!", Toast.LENGTH_SHORT).show();
    }

    private void setRockLocation(int place,int row)
    {
        if(row == 1)
        {
            iv_11.setImageResource(emptyImage);
            iv_12.setImageResource(emptyImage);
            iv_13.setImageResource(emptyImage);

            switch (place)
            {
                case 1:
                    iv_11.setImageResource(frameImage);
                    break;
                case 2:
                    iv_12.setImageResource(frameImage);
                    break;
                case 3:
                    iv_13.setImageResource(frameImage);
                    break;
            }

        }

        if(row == 2)
        {
            iv_21.setImageResource(emptyImage);
            iv_22.setImageResource(emptyImage);
            iv_23.setImageResource(emptyImage);

            switch (place)
            {
                case 1:
                    iv_21.setImageResource(frameImage);
                    break;
                case 2:
                    iv_22.setImageResource(frameImage);
                    break;
                case 3:
                    iv_23.setImageResource(frameImage);
                    break;
            }

        }

        if(row == 3)
        {
            iv_31.setImageResource(emptyImage);
            iv_32.setImageResource(emptyImage);
            iv_33.setImageResource(emptyImage);

            switch (place)
            {
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
            }

        }

        if(row == 4)
        {
            iv_41.setImageResource(emptyImage);
            iv_42.setImageResource(emptyImage);
            iv_43.setImageResource(emptyImage);

            switch (place)
            {
                case 1:
                    iv_41.setImageResource(pawInFrameImage);
                    break;
                case 2:
                    iv_42.setImageResource(pawInFrameImage);
                    break;
                case 3:
                    iv_43.setImageResource(pawInFrameImage);
                    break;
            }

        }

        if(row == 5)
        {
            iv_51.setImageResource(emptyImage);
            iv_52.setImageResource(emptyImage);
            iv_53.setImageResource(emptyImage);

            switch (place)
            {
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
            }

        }

    }

    private int millisToTime(long millis)
    {
        return (int) millis / 1000;
    }

    private void loadImages()
    {
        frameImage=R.drawable.frameimage;
        pawInFrameImage=R.drawable.ic_paw_frame;
        tapImage=R.drawable.ic_tap;
        emptyImage=R.drawable.emptyimage;
    }

}