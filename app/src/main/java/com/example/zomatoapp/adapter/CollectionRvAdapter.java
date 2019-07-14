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
import com.example.zomatoapp.model.collection.Collections;
import com.example.zomatoapp.utils.GradientTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class CollectionRvAdapter extends RecyclerView.Adapter<CollectionRvAdapter.CollectionViewHolder> {

    Context context;
    List<Collections> collectionsList;

    public CollectionRvAdapter(List<Collections> collectionsList, Context context) {
        this.collectionsList = collectionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View collectionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_view,
                parent, false);
        return new CollectionViewHolder(collectionView);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        holder.tittle.setText(collectionsList.get(position).getCollection().getTitle());
        holder.description.setText(collectionsList.get(position).getCollection().getDescription());
        holder.placesCount.setText(String.valueOf(collectionsList.get(position).getCollection().getResCount())+" PLACES ");
        Picasso.with(context).load(collectionsList.get(position).getCollection().getImageUrl())
                .centerCrop().resize(540,280).
                transform(new GradientTransformation())
                .transform(new RoundedCornersTransformation(10, 1))
                .into(holder.banner);
    }

    @Override
    public int getItemCount() {
        return collectionsList.size();
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder{
        ImageView banner;
        TextView tittle;
        TextView description;
        TextView placesCount;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.collection_image);
            tittle = itemView.findViewById(R.id.collection_tittle);
            description = itemView.findViewById(R.id.collection_description);
            placesCount = itemView.findViewById(R.id.no_of_places_text_view);
        }
    }
}
