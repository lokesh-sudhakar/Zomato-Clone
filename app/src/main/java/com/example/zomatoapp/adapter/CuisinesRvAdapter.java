package com.example.zomatoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.R;
import com.example.zomatoapp.model.cuisines.Cuisine;

import java.util.List;

public class CuisinesRvAdapter extends RecyclerView.Adapter<CuisinesRvAdapter.CuisinesViewHolder> {

    public List<Cuisine> cuisinesList;
    private Context context;

    public CuisinesRvAdapter(List<Cuisine> cuisinesList, Context context) {
        this.cuisinesList = cuisinesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CuisinesRvAdapter.CuisinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisines,
                parent, false);
        return new CuisinesRvAdapter.CuisinesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisinesRvAdapter.CuisinesViewHolder holder, int position) {
//        holder.mTitle.setText(cuisinesList.get(position).getRestaurant().getName());
//        holder.mCuisines.setText(cuisinesList.get(position).getRestaurant().getCuisines());
//        holder.mPerPersonCost.setText("" + restaurantList.get(position).getRestaurant().
//                getAverageCostForTwo() / 2 + " per person");
//        holder.mRating.setText(restaurantList.get(position).getRestaurant().getUserRating().
//                getAggregateRating());
//        double rating = Double.parseDouble(restaurantList.get(position).getRestaurant().getUserRating().
//                getAggregateRating());
//        if(rating >= 4.0){
//            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_green);
//        } else if (rating >= 3.5){
//            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_lime_green);
//        } else if (rating >= 3.0){
//            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_yellow_green);
//        } else if (rating >= 1.0){
//            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_orange_green);
//        } else {
//            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_grey);
//        }
//        Picasso.with(context).load(restaurantList.get(position).getRestaurant().getThumb()).
//                transform(new RoundedCornersTransformation(10, 1)).placeholder(R.drawable.placeholder_food).into(holder.mPoster);
//
    }

    @Override
    public int getItemCount() {
        return cuisinesList.size();
    }

    class CuisinesViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mCuisines;
        ImageView mPoster;

        private CuisinesViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title_cuisine);
//            mCuisines = itemView.findViewById(R.id.restaurant_cuisines);
//            mPoster = itemView.findViewById(R.id.restaurant_poster);
        }
    }
}
