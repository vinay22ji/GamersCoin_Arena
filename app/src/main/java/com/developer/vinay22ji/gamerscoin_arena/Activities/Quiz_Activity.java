package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Models.testmodam;
import com.developer.vinay22ji.gamerscoin_arena.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz_Activity extends AppCompatActivity {

    TextView two_question_text;
    Button two_option1,two_option2,two_option3,two_option4;
    LinearLayout layout_one,layout_two;

    public static List<testmodam> Modam_list = new ArrayList<>();
    int selectedtest;
    int totalmarks=0;
    int points=0;
    int positions=1;

    public Quiz_Activity(List<testmodam> modam_list, int position) {
        this.Modam_list=modam_list;
        this.positions=position;
    }

    public Quiz_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_);

        if(new DarkModePrefManager(Quiz_Activity.this).isNightMode()){
            MainActivity.darkmode(Quiz_Activity.this);
        }
        MainActivity.setupMode(this.getWindow(), Quiz_Activity.this);

        init();
    }
    RadioButton radiooption1,radiooption2,radiooption3,radiooption4;
    TextView questiontext,questionnotext;
    RadioGroup radioGroup2;
    Button next_btn;

    private void init()
    {
        two_question_text=findViewById(R.id.two_question_text);
        two_option1=findViewById(R.id.two_option1);
        two_option2=findViewById(R.id.two_option2);
        two_option3=findViewById(R.id.two_option3);
        two_option4=findViewById(R.id.two_option4);
        layout_one=findViewById(R.id.layout_one);
        layout_two=findViewById(R.id.layout_two);

        /////

        next_btn=findViewById(R.id.next_btn);
        questiontext=findViewById(R.id.questiontext);
        radiooption1=findViewById(R.id.radiooption11);
        radiooption2=findViewById(R.id.radiooption21);
        radiooption3=findViewById(R.id.radiooption31);
        radiooption4=findViewById(R.id.radiooption41);
        questionnotext=findViewById(R.id.questionnotext);
        radioGroup2=findViewById(R.id.radiogroup2);


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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion1marks());


            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion2marks());



            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion3marks());

            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion4marks());

            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion5marks());



            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion6marks());



            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion7marks());


            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion8marks());


            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion9marks());


            next_btn.setOnClickListener(new View.OnClickListener() {
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

                        if(positions!=10)
                        {
                            positions++;
                            setquestion(positions);
                        }
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
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

            totalmarks=totalmarks+Integer.parseInt(Modam_list.get(selectedtest).getQuestion10marks());


            next_btn.setOnClickListener(new View.OnClickListener() {
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
                        next_btn.setClickable(false);

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
                        next_btn.setClickable(false);

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
                        next_btn.setClickable(false);

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
                        next_btn.setClickable(false);
                        positions=100;
                        setquestion(positions);
                    }else
                    {
                        Toast.makeText(Quiz_Activity.this, "Select one", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else if(position==100)
        {

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

}