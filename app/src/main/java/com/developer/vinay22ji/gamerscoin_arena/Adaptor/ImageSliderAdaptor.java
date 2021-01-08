package com.developer.vinay22ji.gamerscoin_arena.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.developer.vinay22ji.gamerscoin_arena.Models.SliderModel;
import com.developer.vinay22ji.gamerscoin_arena.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class ImageSliderAdaptor extends SliderViewAdapter<SliderViewHolder> {

    Context context;
    List<SliderModel> gameModel=new ArrayList<>();

    public ImageSliderAdaptor(Context context, List<SliderModel> gameModel){
      this.context = context;
      this.gameModel=gameModel;
  }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, parent,false);

        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder holder, final int position) {

        Glide.with(context)
                .load(gameModel.get(position).getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(0.01f)
                .into(holder.sliderImageview);

        System.out.println("---------------------------- "+gameModel.get(position).getImage());

    }

    @Override
    public int getCount() {
        return gameModel.size();
    }

}

class  SliderViewHolder extends SliderViewAdapter.ViewHolder {
    View itemView;
    ImageView sliderImageview;
    public SliderViewHolder(View itemView) {

        super(itemView);
        this.itemView = itemView;
        sliderImageview = itemView.findViewById(R.id.iv_auto_image_slider);

    }
}
