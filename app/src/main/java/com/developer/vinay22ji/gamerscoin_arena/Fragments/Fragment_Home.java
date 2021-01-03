package com.developer.vinay22ji.gamerscoin_arena.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class Fragment_Home extends Fragment {

    String Uid;
    TextView name_text;
    TickerView currentPoint;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment__home, container, false);

        init(view);
        getdatafrom_Firebase();


        return view;
    }


    public void init(View view)
    {

        if(new DarkModePrefManager(getActivity()).isNightMode()){
            MainActivity.darkmode(getActivity());
        }MainActivity.setupMode(getActivity().getWindow(), getActivity());


        Uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        name_text=view.findViewById(R.id.name_text);
        currentPoint = (TickerView) view.findViewById(R.id.userpointss);
        currentPoint.setCharacterLists(TickerUtils.provideNumberList());
    }

    public void getdatafrom_Firebase()
    {

        FirebaseDatabase.getInstance().getReference().child("users")
                .child(Uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                name_text.setText("Hello "+snapshot.child("name").getValue().toString()+"!");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}