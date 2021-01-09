package com.developer.vinay22ji.gamerscoin_arena.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.vinay22ji.gamerscoin_arena.Activities.WebActivity;
import com.developer.vinay22ji.gamerscoin_arena.Adaptor.ImageSliderAdaptor;
import com.developer.vinay22ji.gamerscoin_arena.DarkModePrefManager;
import com.developer.vinay22ji.gamerscoin_arena.Activities.MainActivity;
import com.developer.vinay22ji.gamerscoin_arena.Models.GameModel;
import com.developer.vinay22ji.gamerscoin_arena.Models.SliderModel;
import com.developer.vinay22ji.gamerscoin_arena.Activities.OnlineGames_Activity;
import com.developer.vinay22ji.gamerscoin_arena.GamesActivity.PianoTiles_Activity;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Home extends Fragment {

    String Uid;
    TextView name_text,more_onlinegames;
    TickerView currentPoint;
    RecyclerView onlinegames_recyclerview;
    CardView Piano_Tiles_Card;
    static SliderView sliderView;
    List<SliderModel> gameModel=new ArrayList<>();
    CircleImageView profileCircleImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view= inflater.inflate(R.layout.fragment__home, container, false);

        init(view);
        getuserData();
        getdatafrom_Firebase();

        return view;
    }


    public void init(View view)
    {

        if(new DarkModePrefManager(getActivity()).isNightMode()){
            MainActivity.darkmode(getActivity());
        }MainActivity.setupMode(getActivity().getWindow(), getActivity());


        Uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        name_text=view.findViewById(R.id.home_name_text);
        currentPoint = (TickerView) view.findViewById(R.id.userpointss);
        currentPoint.setCharacterLists(TickerUtils.provideNumberList());
        onlinegames_recyclerview = view.findViewById(R.id.onlinegames_recyclerview);
        Piano_Tiles_Card = view.findViewById(R.id.Piano_Tiles_Card);
        more_onlinegames = view.findViewById(R.id.more_onlinegames);
        profileCircleImageView = view.findViewById(R.id.profileCircleImageView);
        sliderView = (SliderView) view.findViewById(R.id.imageSlider);


        Piano_Tiles_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PianoTiles_Activity.class));

            }
        });
        more_onlinegames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OnlineGames_Activity.class));
            }
        });
        profileCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCurrentTab(3);
            }
        });


        getSliderdata();
        get_OnlineGames();
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

    public void get_OnlineGames()
    {

        onlinegames_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("onlineGames")
                .limit(4)
                .orderBy("createdAt",Query.Direction.DESCENDING);

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                try {
                    if(value.isEmpty())
                    {
                        Toast.makeText(getActivity(), "No Game Available", Toast.LENGTH_SHORT).show();
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

        adapter = new FirestoreRecyclerAdapter<GameModel, GameModelViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull GameModelViewHolder holder, final int position, @NonNull final GameModel model)
            {
                holder.name.setText(model.getName());
                Picasso.get().load(model.getIcon()).into(holder.icon);

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        WebActivity webActivity=new WebActivity(model.getLink());
                        webActivity.webUrl=model.getLink();
                        startActivity(new Intent(getActivity(), WebActivity.class));

                    }
                });

            }

            @NonNull
            @Override
            public GameModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamemodel_item, parent, false);
                return new GameModelViewHolder(view);
            }
        };

        onlinegames_recyclerview.setAdapter(adapter);
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
                            name_text.setText("Hello "+snapshot.child("name").getValue().toString()+" !");
                        }
                        else
                        {
                            name_text.setText("Hello ");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }





    public void getSliderdata()
    {

        FirebaseDatabase.getInstance().getReference("SliderData")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    sliderView.setVisibility(View.VISIBLE);
                    for(DataSnapshot ss:dataSnapshot.getChildren())
                    {
                        SliderModel m=ss.getValue(SliderModel.class);
                        gameModel.add(m);

                        System.out.println("---------- added ");
                    }

                }
                else
                {
                    sliderView.setVisibility(View.GONE);
                }
                sliderView.setSliderAdapter(new ImageSliderAdaptor(getActivity(),gameModel));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }



    private FirestoreRecyclerAdapter<GameModel, GameModelViewHolder> adapter;
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

}