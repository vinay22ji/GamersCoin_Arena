package com.developer.vinay22ji.gamerscoin_arena.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.developer.vinay22ji.gamerscoin_arena.Models.Modam;
import com.developer.vinay22ji.gamerscoin_arena.Models.testmodam;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class test_activity extends AppCompatActivity {

    Button nextbtn;
    TextView questiontext,questionnotext,_tv,markstext,totalmarkstext,totaltimetext;
    RadioButton radiooption1,radiooption2,radiooption3,radiooption4;
    RadioGroup radioGroup;
    SeekBar seekbar;
    ImageView goback;
    LinearLayout scorelayout;

    int selectedtest;
    int sec;
    RadioGroup radioGroup2;
    CountDownTimer countDownTimer;
    int points=0;
    int totalmarks=0;
    int positions=1;
    Chronometer MyChronometer;
    long startTime, stopTime;
    public boolean running;
    String totaltime;
    public static List<testmodam> Modam_list=new ArrayList<>();

    String testid;

    DatabaseReference dr,dr2;

    Long todattasks;

    public test_activity()
    {

    }

    public test_activity(List<testmodam> Modam_list, int selectedtest) {
        this.Modam_list = Modam_list;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        _tv = (TextView) findViewById( R.id.textView1 );

        synkdata();
        checker();
        running = false;
        stopTime = 0;
        MyChronometer = (Chronometer) findViewById(R.id.chronometer);
        MyChronometer.setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.chronometer)).start();
        startTime= System.currentTimeMillis();
        running = true;

        dr = FirebaseDatabase.getInstance().getReference("groups").child("Maths").child("poll");
        nextbtn=findViewById(R.id.nextbtn);
        questiontext=findViewById(R.id.questiontext);
        radiooption1=findViewById(R.id.radiooption11);
        radiooption2=findViewById(R.id.radiooption21);
        radiooption3=findViewById(R.id.radiooption31);
        radiooption4=findViewById(R.id.radiooption41);
        questionnotext=findViewById(R.id.questionnotext);
        radioGroup2=findViewById(R.id.radiogroup2);
        seekbar=findViewById(R.id.seekbar);
        markstext=findViewById(R.id.markstext);
        totalmarkstext=findViewById(R.id.totalmarkstext);
        scorelayout=findViewById(R.id.scorelayout);
        goback=findViewById(R.id.goback);
        totaltimetext=findViewById(R.id.totaltimetext);

        setquestion(positions);

        selectedtest= Modam.getPosition();
        testid=Modam_list.get(selectedtest).getTestid();

        System.out.println("========================== selected test "+selectedtest);
        System.out.println("========================== selected id "+testid);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        checker();
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        super.onBackPressed();
    }

    public void setquestion(final int position)
    {
        questionnotext.setText("Question "+position+"/10");

        if(position==1)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion1());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion1option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion1option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion1option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion1option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());

            sec=miniconverter(Modam_list.get(selectedtest).getQuestion1time());
            try {
                countDownTimer.cancel();
            }
            catch (NullPointerException v)
            {

            }
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion1answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion1answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion1answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion1answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }
//                    try
//                    {
//                        String selectedText = (String) radioButton.getText();
//                        if (selectedText == null) {
//
//                        } else
//                        {
//                            if(selectedText.equals(Modam_list.get(selectedtest).getQuestion1answer()))
//                            {
//                                int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());
//                                points=points+marks;
//
//                                System.out.println("================ points "+points );
//                            }
//
//                        }
//                        System.out.println("================================== " + selectedText);
//
//
//
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
//
//                    }

                }
            });

        }
        else if(position==2)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion2());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion2option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion2option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion2option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion2option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion2time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion2answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion2answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion2answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion2answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(position==3)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion3());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion3option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion3option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion3option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion3option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion3time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion3answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion3answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion3answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion3answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(position==4)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion4());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion4option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion4option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion4option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion4option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion4time());
            countDownTimer.cancel();
            timer(sec);
            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion4answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion4answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion4answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion4answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else if(position==5)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion5());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion5option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion5option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion5option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion5option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion5time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion5answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion5answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion5answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion5answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else if(position==6)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion6());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion6option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion6option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion6option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion6option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion6time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion6answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion6answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion6answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion6answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(position==7)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion7());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion7option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion7option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion7option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion7option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion7time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion7answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion7answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion7answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion7answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else if(position==8)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion8());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion8option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion8option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion8option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion8option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion8time());
            countDownTimer.cancel();
            timer(sec);
            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion8answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion8answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion8answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion8answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        else if(position==9)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion9());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion9option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion9option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion9option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion9option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion9time());
            countDownTimer.cancel();
            timer(sec);
            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion9answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion9answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion9answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion9answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        countDownTimer.cancel();
                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(position==10)
        {
            questiontext.setText(Modam_list.get(selectedtest).getQuestion10());
            radiooption1.setText(Modam_list.get(selectedtest).getQuestion10option1());
            radiooption2.setText(Modam_list.get(selectedtest).getQuestion10option2());
            radiooption3.setText(Modam_list.get(selectedtest).getQuestion10option3());
            radiooption4.setText(Modam_list.get(selectedtest).getQuestion10option4());

            totalmarks=totalmarks+ Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());

            int sec=miniconverter(Modam_list.get(selectedtest).getQuestion10time());
            countDownTimer.cancel();
            timer(sec);

            nextbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(radiooption1.isChecked())
                    {
                        String selectedText="First";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion10answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        nextbtn.setClickable(false);
                        countDownTimer.cancel();
                        positions=100;
                        setquestion(positions);
                    }
                    else if(radiooption2.isChecked())
                    {
                        String selectedText="Second";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion10answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        nextbtn.setClickable(false);
                        countDownTimer.cancel();
                        positions=100;
                        setquestion(positions);
                    }
                    else if(radiooption3.isChecked())
                    {
                        String selectedText="Third";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion10answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        nextbtn.setClickable(false);
                        countDownTimer.cancel();
                        positions=100;
                        setquestion(positions);

                    }
                    else if(radiooption4.isChecked())
                    {
                        String selectedText="Fourth";
                        if(selectedText.equals(Modam_list.get(selectedtest).getQuestion10answer()))
                        {
                            int marks= Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());
                            points=points+marks;

                            System.out.println("================ points "+points );
                        }
                        nextbtn.setClickable(false);
                        countDownTimer.cancel();
                        positions=100;
                        setquestion(positions);
                    }else
                    {
                        Toast.makeText(test_activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else if(position==100)
        {
            scorelayout.setVisibility(View.VISIBLE);
            markstext.setText(String.valueOf(points));
            totalmarkstext.setText("out of "+ String.valueOf(totalmarks));

            totaltime=MyChronometer.getText().toString();
            totaltimetext.setText("Total Time Taken "+ String.valueOf(totaltime));
            System.out.println("total time----------------------- "+totaltime);
            ((Chronometer) findViewById(R.id.chronometer)).stop();
            stopTime= System.currentTimeMillis();
            running = false;
            submittest();


        }

        radiooption1.setChecked(false);
        radiooption2.setChecked(false);
        radiooption3.setChecked(false);
        radiooption4.setChecked(false);

        radiooption1.setClickable(true);
        radiooption2.setClickable(true);
        radiooption3.setClickable(true);
        radiooption4.setClickable(true);
    }

    public int miniconverter(String min)
    {
        int minsec=0000;

        if(min.equals("30 sec"))
        {
            minsec=30000;
        }
        else if(min.equals("1 min"))
        {
            minsec=60000;
        }
        else if(min.equals("2 min"))
        {
            int i = 120000;
            minsec=i;
        }
        else if(min.equals("5 min"))
        {
            int i = 300000;
            minsec=i;
        }
        else if(min.equals("10 min"))
        {
            int i = 600000;
            minsec=i;

        }
        else if(min.equals("15 min"))
        {
            int i = 900000;
            minsec=i;
        }
        else if(min.equals("30 min"))
        {
            int i = 1800000;
            minsec=i;
        }

        return minsec;
    }

    public void timer(int sec)
    {
        seekbar.setMax(sec);
        countDownTimer=new CountDownTimer(sec, 1000) { // adjust the milli seconds here

            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            public void onTick(long millisUntilFinished) {
                _tv.setText(""+ String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                String d= String.valueOf(millisUntilFinished);
                int s= Integer.parseInt(d);
                seekbar.setProgress(s);
                nextbtn.setClickable(true);
            }

            public void onFinish() {
                _tv.setText("done!");
                Toast.makeText(test_activity.this, "Time Up !", Toast.LENGTH_SHORT).show();
                nextbtn.setClickable(false);

                if(positions!=10)
                {
                    nextbtn.setClickable(false);
                    positions++;
                    setquestion(positions);
                }
                else
                {
                    positions=100;
                    setquestion(positions);
                    scorelayout.setVisibility(View.VISIBLE);
                    markstext.setText(String.valueOf(points));
                    totalmarkstext.setText("out of "+ String.valueOf(totalmarks));

                    totaltime=MyChronometer.getText().toString();
                    totaltimetext.setText("Total Time Taken "+ String.valueOf(totaltime));
                    System.out.println("total time----------------------- "+totaltime);
                    ((Chronometer) findViewById(R.id.chronometer)).stop();
                    stopTime= System.currentTimeMillis();
                    running = false;

                    submittest();

                }

            }
        }.start();

    }

    public void synkdata()
    {
//        try {
//            dr = FirebaseDatabase.getInstance().getReference("groups").child(My_Recycler_Adapter.groupname).child("tests").child(testid);
//            checker();
//        } catch (NullPointerException e) {
//            try {
//                dr = FirebaseDatabase.getInstance().getReference("groups").child(My_Recycler_Adapter.firstgroupname).child("tests")
//                        .child(testid);
//                checker();
//            } catch (NullPointerException d) {
//
//            }
//        }
    }

    public void checker()
    {

        if(dr==null)
        {
            synkdata();
        }
        else
        {
            dr.child("result").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                        String marks=snapshot.child("marks").getValue().toString();
                        String time=snapshot.child("time").getValue().toString();
                        String totalmarks=snapshot.child("totalmarks").getValue().toString();

                        countDownTimer.cancel();

                        View adContainer = findViewById(R.id.banneradViewtest2);
                        
                        scorelayout.setVisibility(View.VISIBLE);
                        markstext.setText(String.valueOf(marks));
                        totalmarkstext.setText("out of "+ String.valueOf(totalmarks));
                        totaltime=MyChronometer.getText().toString();
                        totaltimetext.setText("Total Time Taken "+ String.valueOf(time));
                    }
                    catch (Exception f)
                    {
                        View adContainer = findViewById(R.id.banneradViewtest);
                        f.printStackTrace();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

//    RewardedAd rewardedAd;

    public void submittest()
    {
        dr.child("result").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("uid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dr.child("result").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("marks").setValue(points);
        dr.child("result").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("time").setValue(totaltime);
        dr.child("result").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("totalmarks").setValue(totalmarks);

//        String a= String.valueOf(fragment_Home.Done);
//        int i= Integer.parseInt(a);
//        i++;
//        fragment_Home.donetodaytasks();
//        fragment_Home.dr7.child("members").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("todaytasks").setValue(i);


    }

}