package com.developer.vinay22ji.gamerscoin_arena.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.vinay22ji.gamerscoin_arena.Activities.EditProfile_Activity;
import com.developer.vinay22ji.gamerscoin_arena.Activities.Login_Activity;
import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Activities.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class Fragment_Profile extends Fragment {

    CardView Edit_card,logout_card;
    TickerView currentPoint;
    TextView name_text_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__profile, container, false);
        if(new DarkModePrefManager(getActivity()).isNightMode()){
            MainActivity.darkmode(getActivity());
        }MainActivity.setupMode(getActivity().getWindow(), getActivity());



        init(view);
        getuserData();
        return view;

    }

    public void init(View view)
    {
        Edit_card= view.findViewById(R.id.Edit_card);
        currentPoint = (TickerView) view.findViewById(R.id.userpointss_profile);
        currentPoint.setCharacterLists(TickerUtils.provideNumberList());
        logout_card = view.findViewById(R.id.logout_card);
        name_text_profile = view.findViewById(R.id.name_text_profile);

        Edit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfile_Activity.class));
            }
        });
        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), Login_Activity.class));
            }
        });
    }

    private void getuserData()
    {
        FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (!snapshot.child("name").getValue().toString().equals(""))
                        {
                            name_text_profile.setText(snapshot.child("name").getValue().toString());
                        }
                        else
                        {
                            name_text_profile.setText("Hii Complete your profile");
                        }

                        currentPoint.setText(snapshot.child("wallet").getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}