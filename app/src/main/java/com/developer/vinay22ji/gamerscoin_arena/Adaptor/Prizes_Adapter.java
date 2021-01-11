package com.developer.vinay22ji.gamerscoin_arena.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.developer.vinay22ji.gamerscoin_arena.Models.PrizeModel;
import com.developer.vinay22ji.gamerscoin_arena.R;

import java.util.ArrayList;
import java.util.List;

public class Prizes_Adapter extends RecyclerView.Adapter<Prizes_Adapter.MyHolder> {

    private List<PrizeModel> model=new ArrayList<>();
    Context context;

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void addmodam(PrizeModel m)
    {
        model.add(m);
    }

    @NonNull
    @Override
    public Prizes_Adapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gamemodel_item_large,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Prizes_Adapter.MyHolder holder, final int position) {

        String imageid="https://cdn2.iconfinder.com/data/icons/social-icons-color/512/paytm-512.png";

        try {
            Glide.with(context)
                    .load(imageid)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .thumbnail(0.01f)
                    .into(holder.icon);
        } catch (Exception ed) { }

        holder.name.setText(model.get(position).getName());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {

        private TextView name;
        private ImageView icon;
        private LinearLayout card;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.gamemodel_icon);
            name=itemView.findViewById(R.id.gamemodel_name);
            card=itemView.findViewById(R.id.gamemodel_card);
            name.setVisibility(View.VISIBLE);
        }
    }

}
