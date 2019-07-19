package com.example.zomatoapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.R;
import com.example.zomatoapp.model.Location;
import com.example.zomatoapp.model.RestaurantData;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class RestaurantRvAdapter extends RecyclerView.Adapter<RestaurantRvAdapter.RestaurantViewHolder> {

    public List<RestaurantData> restaurantList;
    private Context context;
    OnClickRestaurant onClickRestaurant;


    public interface OnClickRestaurant{
        void onPerformClick(String id);
    }
    public RestaurantRvAdapter(List<RestaurantData> restaurantList, Context context,OnClickRestaurant onClickRestaurant) {
        this.restaurantList = restaurantList;
        this.context = context;
        this.onClickRestaurant = onClickRestaurant;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list,
                parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, final int position) {
        holder.mTitle.setText(restaurantList.get(position).getRestaurant().getName());
        holder.mCuisines.setText(restaurantList.get(position).getRestaurant().getCuisines());
        holder.mPerPersonCost.setText("" + restaurantList.get(position).getRestaurant().
                                      getAverageCostForTwo() / 2 + " per person");
        holder.mRating.setText(restaurantList.get(position).getRestaurant().getUserRating().
                getAggregateRating());
        double rating = Double.parseDouble(restaurantList.get(position).getRestaurant().getUserRating().
                getAggregateRating());
        if(rating >= 4.0){
            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_green);
        } else if (rating >= 3.5){
            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_lime_green);
        } else if (rating >= 3.0){
            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_yellow_green);
        } else if (rating >= 1.0){
            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_orange_green);
        } else {
            holder.mRating.setBackgroundResource(R.drawable.rounded_corner_grey);
        }
        if(!restaurantList.get(position).getRestaurant().getThumb().isEmpty()) {
            Picasso.with(context).load(restaurantList.get(position).getRestaurant().getThumb()).
                    transform(new RoundedCornersTransformation(10, 1)).placeholder(R.drawable.placeholder_food).into(holder.mPoster);
        }
        holder.restaurantLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRestaurant.onPerformClick(restaurantList.get(position).getRestaurant().getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        TextView mCuisines;
        TextView mPerPersonCost;
        TextView mRating;
        ImageView mPoster;
        ConstraintLayout restaurantLayout;

        private RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.restaurant_name);
            mCuisines = itemView.findViewById(R.id.restaurant_cuisines);
            mPerPersonCost = itemView.findViewById(R.id.per_person_cost);
            mRating = itemView.findViewById(R.id.rating);
            mPoster = itemView.findViewById(R.id.restaurant_poster);
            restaurantLayout = itemView.findViewById(R.id.restaurant_layout);
        }

    }
}
