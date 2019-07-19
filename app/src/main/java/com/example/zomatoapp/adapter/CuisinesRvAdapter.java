package com.example.zomatoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.R;
import com.example.zomatoapp.model.cuisines_model.Cuisines;
import com.squareup.picasso.Picasso;;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CuisinesRvAdapter extends RecyclerView.Adapter<CuisinesRvAdapter.CuisinesViewHolder> {

    private List<Cuisines> cuisinesList;
    private Context context;
    OnClickCuisine onClickCuisine;

    public CuisinesRvAdapter(List<Cuisines> cuisinesList, Context context, OnClickCuisine onClickCuisine) {
        this.cuisinesList = cuisinesList;
        this.context = context;
        this.onClickCuisine = onClickCuisine;
    }

    @NonNull
    @Override
    public CuisinesRvAdapter.CuisinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisines,
                parent, false);
        return new CuisinesRvAdapter.CuisinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisinesRvAdapter.CuisinesViewHolder holder,final int position) {
        holder.mTitle.setText(cuisinesList.get(position).getCuisineName());
        int productImageId = context.getResources().getIdentifier(cuisinesList.get(position).getImage(),"drawable", context.getPackageName());
        Picasso.with(context).load(productImageId).transform(new RoundedCornersTransformation(15, 1)).
                placeholder(R.drawable.placeholder_food).into(holder.mPoster);
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCuisine.onPerformClick(cuisinesList.get(position).getCuisineId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuisinesList.size();
    }

    class CuisinesViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        ImageView mPoster;
        FrameLayout frameLayout;

        private CuisinesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title_cuisine);
            mPoster = itemView.findViewById(R.id.image_cuisine);
            frameLayout = itemView.findViewById(R.id.cuisines_view);
        }
    }

    public interface OnClickCuisine{
        void onPerformClick(int id);
    }
}
