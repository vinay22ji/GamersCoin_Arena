package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.Adaptor.Prizes_Adapter;
import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Models.GameModel;
import com.developer.vinay22ji.gamerscoin_arena.Models.PrizeModel;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class Prizes_Activity extends AppCompatActivity {

    RecyclerView Prizes_recyclerview;

    //  holder.name.setText(model.getName());
    //                Picasso.get().load("https://cdn2.iconfinder.com/data/icons/social-icons-color/512/paytm-512.png").into(holder.icon);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prizes_);

        if(new DarkModePrefManager(Prizes_Activity.this).isNightMode()){
            MainActivity.darkmode(Prizes_Activity.this);
        }
        MainActivity.setupMode(this.getWindow(), Prizes_Activity.this);

        findViewById(R.id.PrizeActivityback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Prizes_recyclerview = findViewById(R.id.Prizes_recyclerview);
        get_Prizes();

    }


    public void get_Prizes()
    {

        Prizes_recyclerview.setLayoutManager(new GridLayoutManager(Prizes_Activity.this,3));

        DatabaseReference dr2= FirebaseDatabase.getInstance().getReference().child("prizes").child("paytm");
        dr2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    Prizes_Adapter mr=new Prizes_Adapter();
                    mr.setContext(Prizes_Activity.this);
                    for(DataSnapshot ss:dataSnapshot.getChildren())
                    {
                        PrizeModel m=ss.getValue(PrizeModel.class);
                        mr.addmodam(m);
                    }
                    Prizes_recyclerview.setHasFixedSize(true);
                    Prizes_recyclerview.setAdapter(mr);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Prizes_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}