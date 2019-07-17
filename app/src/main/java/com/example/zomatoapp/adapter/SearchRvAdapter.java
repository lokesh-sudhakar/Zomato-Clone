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
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class SearchRvAdapter extends RecyclerView.Adapter<SearchRvAdapter.RestaurantViewHolder>{
    public List<RestaurantData> restaurantList;
    private Context context;

    public SearchRvAdapter(List<RestaurantData> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchRvAdapter.RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,
                parent, false);
        return new SearchRvAdapter.RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRvAdapter.RestaurantViewHolder holder, int position) {
        holder.mTitle.setText(restaurantList.get(position).getRestaurant().getName());
        holder.mData.setText(restaurantList.get(position).getRestaurant().getCuisines());
        if (!restaurantList.get(position).getRestaurant().getThumb().equals("")) {
            Log.d("searchCall"," bind " + restaurantList.get(position).getRestaurant().getThumb() );
            Picasso.with(context).load(restaurantList.get(position).getRestaurant().getThumb()).
                    transform(new RoundedCornersTransformation(10, 0)).
                    placeholder(R.drawable.placeholder_food).error(R.drawable.placeholder_food).into(holder.mPoster);
        } else {
            Log.d("searchCall"," np bind " + restaurantList.get(position).getRestaurant().getThumb() );
            Picasso.with(context).load(R.drawable.dummy_food).
                    transform(new RoundedCornersTransformation(10, 0)).
                    placeholder(R.drawable.placeholder_food).error(R.drawable.zomato).into(holder.mPoster);
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mData;
        ImageView mPoster;

        private RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.search_title);
            mData = itemView.findViewById(R.id.search_data);
            mPoster = itemView.findViewById(R.id.imageView_search);
        }
    }
}
