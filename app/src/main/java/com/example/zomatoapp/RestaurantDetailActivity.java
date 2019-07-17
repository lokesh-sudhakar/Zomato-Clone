package com.example.zomatoapp;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.ReviewAdapter;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.ReviewsApi;
import com.example.zomatoapp.viewModels.RestaurantDetailViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class RestaurantDetailActivity extends AppCompatActivity {

    private RestaurantDetailViewModel restaurantDetailViewModel;


    String restaurantId;
    String location;
    double lattitude;
    double longitude;

    ImageView restaurantPoster;
    Toolbar toolbar;
    CollapsingToolbarLayout toolbarLayout;
    AppBarLayout appBar;
    TextView restaurantName;
    TextView cusinesText;
    TextView rating;
    TextView address;
    TextView restaurantStatus;
    TextView restaurantTiming;
    TextView reviewCount;
    RecyclerView reviewRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        restaurantPoster = findViewById(R.id.restaurant_poster);
        toolbar = findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(" ");
        appBar = findViewById(R.id.app_bar);
        restaurantName = findViewById(R.id.restaurant_name);
        cusinesText = findViewById(R.id.cusines_text);
        rating = findViewById(R.id.rating);
        address = findViewById(R.id.address);
        restaurantStatus =findViewById(R.id.restaurant_status);
        restaurantTiming = findViewById(R.id.restaurant_timing);
        reviewCount = findViewById(R.id.review_count);
        reviewRv = findViewById(R.id.review_rv);

        restaurantDetailViewModel = ViewModelProviders.of(this).get(RestaurantDetailViewModel.class);

        ((AppCompatActivity) Objects.requireNonNull(this)).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) this).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back", "pressed");
                onBackPressed();
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            restaurantId = bundle.getString("restaurant_id");
            location = bundle.getString("places");
            Log.d("resid", restaurantId + "  " + location);

        }
        restaurantDetailViewModel.fetchRestaurantDetails(restaurantId).observe(this, new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                restaurantDetailViewModel.setRestaurant(restaurant);
                appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = true;
                    int scrollRange = -1;

                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                        if (scrollRange == -1) {
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }
                        if (scrollRange + verticalOffset == 0) {
                            toolbarLayout.setTitle(restaurantDetailViewModel.getRestaurant().getName());
                            toolbarLayout.setCollapsedTitleGravity(0);
                            toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.dark_black));
                            isShow = true;
                        } else if (isShow) {
                            toolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                            isShow = false;
                        }
                    }
                });
                restaurantName.setText(restaurantDetailViewModel.getRestaurant().getName());
                cusinesText.setText(restaurantDetailViewModel.getRestaurant().getCuisines());
                address.setText(restaurantDetailViewModel.getRestaurant().getLocation().getAddress());
                restaurantTiming.setText(restaurantDetailViewModel.getRestaurant().getTimings()+" (Today)");
                reviewCount.setText(restaurantDetailViewModel.getRestaurant().getAllReviewsCount()+" reviews");

                Picasso.with(getApplication()).load(restaurantDetailViewModel.getRestaurant().getFeaturedImage())
                        .placeholder(R.drawable.placeholder_food).into(restaurantPoster);
                rating.setText(restaurantDetailViewModel.getRestaurant().getUserRating().getAggregateRating());
                Location startPoint = new Location("locationA");
                startPoint.setLatitude(lattitude);
                startPoint.setLongitude(longitude);

                Location endPoint = new Location("locationA");
                endPoint.setLatitude(Double.parseDouble(restaurantDetailViewModel.getRestaurant().getLocation().getLatitude()));
                endPoint.setLongitude(Double.parseDouble(restaurantDetailViewModel.getRestaurant().getLocation().getLongitude()));
                Log.d("location_cur", lattitude + " " + longitude);
                Log.d("location_restaurant", Double.parseDouble(restaurantDetailViewModel.getRestaurant().getLocation().getLatitude())
                        + "  " + Double.parseDouble(restaurantDetailViewModel.getRestaurant().getLocation().getLatitude()));
                double distanceInMeters = startPoint.distanceTo(endPoint);
                double distanceInKiloMeters = distanceInMeters / 1000;
//                if(distanceInKiloMeters>14){
//                    deliveryTime.setText("Delivery in 30-40 minutes.");
//                }else if(distanceInKiloMeters>10){
//                    deliveryTime.setText("Delivery in 25-30 minutes.");
//                }else if(distanceInKiloMeters>5){
//                    deliveryTime.setText("Delivery in 20-25 minutes.");
//                }


            }
        });

        restaurantDetailViewModel.fetchReview(restaurantId).observe(this, new Observer<ReviewsApi>() {
            @Override
            public void onChanged(ReviewsApi reviewsApi) {
                restaurantDetailViewModel.setReviewsApi(reviewsApi);
                setReviewAdapter();
                if(reviewsApi==null){
                    Log.d("review","null");
                }
            }
        });
    }

    public void setReviewAdapter(){
        if(restaurantDetailViewModel.getReviewsApi()!=null){
        ReviewAdapter adapter = new ReviewAdapter(restaurantDetailViewModel.getReviewsApi().
                getUserReviews(),getApplicationContext());

        reviewRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        reviewRv.setAdapter(adapter);
        }
    }
//    @OnClick({R.id.address, R.id.change})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.address:
//                break;
//            case R.id.change:
//                break;
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share: {
                Toast.makeText(this, "shered", Toast.LENGTH_SHORT);
                return true;
            }
            case R.id.action_bookmark: {
                Toast.makeText(this, "bookmarked", Toast.LENGTH_SHORT);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}