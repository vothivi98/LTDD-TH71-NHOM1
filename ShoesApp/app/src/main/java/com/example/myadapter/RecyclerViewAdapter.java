package com.example.myadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoesapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Integer> imageURLs = new ArrayList<>();
    private Context context;


    public RecyclerViewAdapter(Context context, ArrayList<Integer> imageURLs) {
        this.imageURLs = imageURLs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circleimageviewlogo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onCreateViewHolder: called.");
        Glide.with(context).asBitmap().load(imageURLs.get(position)).into(holder.imageLogo);
    }

    @Override
    public int getItemCount() {
        return imageURLs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageLogo;
        public ViewHolder (View itemview) {
            super(itemview);
            imageLogo = itemview.findViewById(R.id.imageLogo);
        }
    }
}
