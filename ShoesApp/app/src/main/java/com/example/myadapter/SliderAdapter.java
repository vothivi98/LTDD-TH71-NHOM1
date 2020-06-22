package com.example.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.model.ImageProduct;
import com.example.shoesapp.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private ArrayList<ImageProduct> sliderItems;
    private ViewPager2 viewPager2;
    Context context;
    public SliderAdapter(ArrayList<ImageProduct> sliderItems, ViewPager2 viewPager2, Context c) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.context = c;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        //holder.(sliderItems.get(position));
        Picasso.with(context).load(sliderItems.get(position).getImg())

                .centerCrop()
                .resize(550, 700)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder
    {
        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemview){
            super(itemview);
            imageView = itemview.findViewById(R.id.imageSlide);
        }

    }
}
