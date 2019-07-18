package com.example.zomatoapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.R;
import com.example.zomatoapp.model.RestaurantData;
import com.example.zomatoapp.utils.GradientTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class EstablishmentInnerRVAdapter extends RecyclerView.Adapter<EstablishmentInnerRVAdapter.EstablishmentViewHolder> {


    private List<RestaurantData> restaurantList;
    private Context context;
    public EstablishmentInnerRVAdapter(List<RestaurantData> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public EstablishmentInnerRVAdapter.EstablishmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View forYouView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.establishment_inner_recycler_view,
                parent, false);
        return  new EstablishmentViewHolder(forYouView);
    }

    @Override
    public void onBindViewHolder(@NonNull EstablishmentInnerRVAdapter.EstablishmentViewHolder holder, int position) {
        if(restaurantList.get(position).getRestaurant()!=null) {

            Log.d("lokesh", "in side adapter" + restaurantList.get(position).getRestaurant().getCuisines());
            holder.cuisines.setText(restaurantList.get(position).getRestaurant().getCuisines());
            holder.locality.setText(restaurantList.get(position).getRestaurant().getLocation().getLocalityVerbose().toUpperCase());
            holder.name.setText(restaurantList.get(position).getRestaurant().getName());
            holder.rating.setText(restaurantList.get(position).getRestaurant().getUserRating().getAggregateRating());
            if(!restaurantList.get(position).getRestaurant().getThumb().isEmpty()) {
                Picasso.with(context).load(restaurantList.get(position).getRestaurant().getThumb())
                        .transform(new GradientTransformation())
                        .transform(new RoundedCornersTransformation(10, 1))
                        .into(holder.restaurantPoster);
            }
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class EstablishmentViewHolder extends RecyclerView.ViewHolder {
        ImageView restaurantPoster;
        TextView name;
        TextView locality;
        TextView rating;
        TextView cuisines;
        public EstablishmentViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantPoster = itemView.findViewById(R.id.restaurant_poster);
            name = itemView.findViewById(R.id.restaurant_name);
            locality = itemView.findViewById(R.id.locatilty);
            rating = itemView.findViewById(R.id.rating);
            cuisines = itemView.findViewById(R.id.cuisines);
        }
    }
}
