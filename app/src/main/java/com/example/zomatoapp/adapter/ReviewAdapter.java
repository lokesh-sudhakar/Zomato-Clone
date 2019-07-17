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
import com.example.zomatoapp.model.UserReview;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    List<UserReview> userReviews;

    public ReviewAdapter(List<UserReview> userReviews, Context context) {
        this.userReviews = userReviews;
        this.context = context;
    }


    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_layout,
                parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.userName.setText(userReviews.get(position).getReview().getUser().getName());
        holder.ratingByUser.setText(userReviews.get(position).getReview().getRating()+" *");
        holder.reviewTime.setText(userReviews.get(position).getReview().getReviewTimeFriendly());
        holder.reviewContent.setText(userReviews.get(position).getReview().getReviewText());

        Picasso.with(context).load(userReviews.get(position).getReview().getUser().getProfileImage()).
                placeholder(R.drawable.placeholder_food).transform(new CropCircleTransformation()).into(holder.profilePicture);

    }

    @Override
    public int getItemCount() {
        return userReviews.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView userName;
        TextView reviewCount;
        TextView userFollowers;
        TextView ratingByUser;
        TextView reviewTime;
        TextView reviewContent;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.profile_picture);
            userName = itemView.findViewById(R.id.user_name);
            reviewCount = itemView.findViewById(R.id.review_count);
            userFollowers = itemView.findViewById(R.id.user_followers);
            ratingByUser = itemView.findViewById(R.id.rating_by_user);
            reviewTime = itemView.findViewById(R.id.review_timestamp);
            reviewContent = itemView.findViewById(R.id.review_content);
        }
    }
}
