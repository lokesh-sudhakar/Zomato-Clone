package com.example.zomatoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.R;

import java.util.List;

public class CuisineMoreInfoAdapter extends RecyclerView.Adapter<CuisineMoreInfoAdapter.CuisinesMoreInfoViewAdapter> {

    String[] cuisinesList;

    public CuisineMoreInfoAdapter(String[] cuisinesList) {
        this.cuisinesList = cuisinesList;
    }


    @NonNull
    @Override
    public CuisineMoreInfoAdapter.CuisinesMoreInfoViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisines_list_view,
                parent, false);
        return new CuisinesMoreInfoViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineMoreInfoAdapter.CuisinesMoreInfoViewAdapter holder, int position) {
        holder.cuisinesName.setText(cuisinesList[position]);
    }

    @Override
    public int getItemCount() {
        if(cuisinesList.length>4){
            return 4;
        }else{
            return cuisinesList.length;
        }
    }

    public class CuisinesMoreInfoViewAdapter extends RecyclerView.ViewHolder{
        TextView cuisinesName;

        public CuisinesMoreInfoViewAdapter(@NonNull View itemView) {
            super(itemView);
            cuisinesName  = itemView.findViewById(R.id.cusine_in_moreinfo);
        }
    }
}
