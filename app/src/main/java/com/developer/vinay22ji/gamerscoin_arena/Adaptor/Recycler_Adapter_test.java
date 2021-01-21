package com.developer.vinay22ji.gamerscoin_arena.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.vinay22ji.gamerscoin_arena.Activities.Quiz_Activity;
import com.developer.vinay22ji.gamerscoin_arena.Activities.test_activity;
import com.developer.vinay22ji.gamerscoin_arena.Models.Modam;
import com.developer.vinay22ji.gamerscoin_arena.Models.testmodam;
import com.developer.vinay22ji.gamerscoin_arena.R;

import java.util.ArrayList;
import java.util.List;

public class Recycler_Adapter_test extends RecyclerView.Adapter<Recycler_Adapter_test.MyHolder> {

    public static List<testmodam> Modam_list = new ArrayList<>();
    Context context;
    String groupname;

    List<String> pollid;

    public Recycler_Adapter_test(@NonNull Context context,String groupname) {
        this.context = context;
        this.groupname = groupname;
    }

    public void addmodam(testmodam m) {
        Modam_list.add(m);
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testview, parent, false);
        System.out.println("===============test view added");
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {


        holder.textname.setText(Modam_list.get(position).getTestname());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                System.out.println("=================== position "+position);
                Modam.setPosition(position);
                test_activity test_activity=new test_activity(Modam_list,position,groupname);
                context.startActivity(new Intent(context,test_activity.class));

            }
        });


    }

    @Override
    public int getItemCount() {

        System.out.println("================= size "+Modam_list.size());
        return Modam_list.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {

        View view;
        TextView textname;
        public MyHolder(View itemView){
            super(itemView);
            view = itemView;
            textname =itemView.findViewById(R.id.testname);

            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            int position=getAdapterPosition();
            Toast.makeText(context, Modam_list.get(position).getTestid(), Toast.LENGTH_SHORT).show();
            Modam_list.remove(position);
            notifyItemRemoved(position);
            return true;
        }
    }


}
