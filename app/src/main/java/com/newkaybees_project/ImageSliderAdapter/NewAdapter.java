package com.newkaybees_project.ImageSliderAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newkaybees_project.ModelClasses.MyHome_ImageSlider_ModelClass;
import com.newkaybees_project.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class NewAdapter extends SliderViewAdapter<NewAdapter.Holder> {
    ArrayList<MyHome_ImageSlider_ModelClass> saqib = new ArrayList<>();

   Context context;

    public NewAdapter(ArrayList<MyHome_ImageSlider_ModelClass> saqib, Context context) {
        this.saqib = saqib;
        this.context = context;
    }

    @Override
    public NewAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imagecard,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(NewAdapter.Holder viewHolder, int position) {
        Glide
                .with(context)
                .load(saqib.get(position).getSliderimages())
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_home_24)
                .into(viewHolder.image);
    }

    @Override
    public int getCount() {
        return saqib.size();
    }

    public class Holder extends ViewHolder {
        ImageView image;
        public Holder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
