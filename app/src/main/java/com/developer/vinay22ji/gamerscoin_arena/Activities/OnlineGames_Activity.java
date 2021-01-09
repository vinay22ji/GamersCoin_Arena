package com.developer.vinay22ji.gamerscoin_arena.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Models.GameModel;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class OnlineGames_Activity extends AppCompatActivity {

    RecyclerView onlinegames_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_games_);

        if(new DarkModePrefManager(this).isNightMode()){
            MainActivity.darkmode(this);
        }MainActivity.setupMode(getWindow(), this);

        onlinegames_recyclerview = findViewById(R.id.onlinegames_recyclerview);
        get_OnlineGames();

    }

    public void get_OnlineGames()
    {

        onlinegames_recyclerview.setLayoutManager(new GridLayoutManager(OnlineGames_Activity.this,3));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("onlineGames")
                .orderBy("createdAt",Query.Direction.DESCENDING);

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                try {
                    if(value.isEmpty())
                    {
                        Toast.makeText(OnlineGames_Activity.this, "No Game Available", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                    }
                }
                catch (NullPointerException n)
                {

                }

            }
        });

        FirestoreRecyclerOptions<GameModel> options = new FirestoreRecyclerOptions.Builder<GameModel>()
                .setQuery(query, GameModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<GameModel, OnlineGames_Activity.GameModelViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull OnlineGames_Activity.GameModelViewHolder holder, final int position, @NonNull final GameModel model)
            {
                holder.name.setText(model.getName());
                Picasso.get().load(model.getIcon()).into(holder.icon);

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        WebActivity webActivity=new WebActivity(model.getLink());
                        webActivity.webUrl=model.getLink();
                        startActivity(new Intent(OnlineGames_Activity.this, WebActivity.class));

                    }
                });

            }

            @NonNull
            @Override
            public OnlineGames_Activity.GameModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamemodel_item_large, parent, false);
                return new OnlineGames_Activity.GameModelViewHolder(view);
            }
        };

        onlinegames_recyclerview.setAdapter(adapter);
    }

    private FirestoreRecyclerAdapter<GameModel, OnlineGames_Activity.GameModelViewHolder> adapter;
    private class GameModelViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView icon;
        private LinearLayout card;
        public GameModelViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.gamemodel_icon);
            name=itemView.findViewById(R.id.gamemodel_name);
            card=itemView.findViewById(R.id.gamemodel_card);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

}