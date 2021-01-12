package com.developer.vinay22ji.gamerscoin_arena.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.vinay22ji.gamerscoin_arena.Activities.View_Test_Activity;
import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Activities.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.R;


public class Fragment_Task extends Fragment {

    CardView Quiz_card;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__task, container, false);

        if(new DarkModePrefManager(getActivity()).isNightMode()){
            MainActivity.darkmode(getActivity());
        }
        MainActivity.setupMode(getActivity().getWindow(), getActivity());


        init(view);

        return view;
    }

    private void init(View view)
    {
        Quiz_card=view.findViewById(R.id.Quiz_card);


        Quiz_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), View_Test_Activity.class));
            }
        });
    }

}