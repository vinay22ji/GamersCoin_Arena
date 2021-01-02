package com.developer.vinay22ji.gamerscoin_arena.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.R;


public class Fragment_Task extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment__task, container, false);

        if(new DarkModePrefManager(getActivity()).isNightMode()){
            MainActivity.darkmode(getActivity());
        }
        MainActivity.setupMode(getActivity().getWindow(), getActivity());
        return view;

    }
}