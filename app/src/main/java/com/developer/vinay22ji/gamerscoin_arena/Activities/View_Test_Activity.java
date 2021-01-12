package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.Adaptor.Recycler_Adapter_test;
import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Models.testmodam;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class View_Test_Activity extends AppCompatActivity {

    RecyclerView recyclerViewtest;
    DatabaseReference dr2;
    String GroupName="Maths";

    public View_Test_Activity(String groupName) {
        GroupName = groupName;
    }

    public View_Test_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__test_);

        if(new DarkModePrefManager(View_Test_Activity.this).isNightMode()){
            MainActivity.darkmode(View_Test_Activity.this);
        }else {

        }
        MainActivity.setupMode(this.getWindow(), View_Test_Activity.this);


        findViewById(R.id.View_Activityback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerViewtest=findViewById(R.id.sheetrecyclerviewtest);
        dr2 = FirebaseDatabase.getInstance().getReference("Quizs").child(GroupName).child("tests");
        dr2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Recycler_Adapter_test mr=new Recycler_Adapter_test(View_Test_Activity.this);
                mr.Modam_list.clear();
                for(DataSnapshot ss:dataSnapshot.getChildren()) {
                    testmodam m = ss.getValue(testmodam.class);
                    mr.addmodam(m);
//                    testavailabe=true;
                }

                recyclerViewtest.setLayoutManager(new LinearLayoutManager(View_Test_Activity.this));
                recyclerViewtest.setHasFixedSize(true);
                recyclerViewtest.setAdapter(mr);

//                availableornot();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(View_Test_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}