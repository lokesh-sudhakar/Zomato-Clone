package com.example.zomatoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.CuisineMoreInfoAdapter;
import com.example.zomatoapp.adapter.ReviewAdapter;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.ReviewsApi;
import com.example.zomatoapp.viewModels.RestaurantDetailViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
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
    String shareRestaurantLink;
    TextView moreInfo;
    RecyclerView cuisinesList;
    View sheetView;

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
        moreInfo = findViewById(R.id.more_info_text_view);
        sheetView =  getLayoutInflater().inflate(R.layout.restaurant_more_info,null);
        cuisinesList =sheetView.findViewById(R.id.cuisines_list);



        final BottomSheetDialog mBottomSheetDialog=new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(sheetView);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.show();
                String[] cuisinesArray = restaurantDetailViewModel.getRestaurant().getCuisines().split(",");

                CuisineMoreInfoAdapter cuisineMoreInfoAdapter = new CuisineMoreInfoAdapter(cuisinesArray);
                cuisinesList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false));

                cuisinesList.setAdapter(cuisineMoreInfoAdapter);
                sheetView.findViewById(R.id.dismissBottomSheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBottomSheetDialog.cancel();
                        mBottomSheetDialog.dismiss();

                    }
                });
            }
        });
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                mBottomSheetDialog.cancel();
                mBottomSheetDialog.dismiss();
            }
        });

        restaurantDetailViewModel = ViewModelProviders.of(this).get(RestaurantDetailViewModel.class);

        ((AppCompatActivity) Objects.requireNonNull(this)).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) this).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) this).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        setSupportActionBar(toolbar);
        toolbarLayout.setContentScrim(null);
        toolbarLayout.setStatusBarScrim(null);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
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
                            toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
                            isShow = true;
                        } else if (isShow) {
                            toolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                            isShow = false;
                        }
                    }
                });


                shareRestaurantLink = restaurantDetailViewModel.getRestaurant().getUrl();
                restaurantName.setText(restaurantDetailViewModel.getRestaurant().getName());
                cusinesText.setText(restaurantDetailViewModel.getRestaurant().getCuisines());
                address.setText(restaurantDetailViewModel.getRestaurant().getLocation().getAddress());
                restaurantTiming.setText(restaurantDetailViewModel.getRestaurant().getTimings());
                reviewCount.setText(restaurantDetailViewModel.getRestaurant().getAllReviewsCount()+" reviews");

                Picasso.with(getApplication()).load(restaurantDetailViewModel.getRestaurant().getFeaturedImage())
                        .placeholder(R.drawable.placeholder_food).into(restaurantPoster);
                double ratingByUser = Double.parseDouble(restaurantDetailViewModel.getRestaurant().getUserRating().getAggregateRating());

                if(ratingByUser >= 4.0){
                    rating.setBackgroundResource(R.drawable.rounded_corner_green);
                } else if (ratingByUser >= 3.5){
                    rating.setBackgroundResource(R.drawable.rounded_corner_lime_green);
                } else if (ratingByUser >= 3.0){
                    rating.setBackgroundResource(R.drawable.rounded_corner_yellow_green);
                } else if (ratingByUser >= 1.0){
                    rating.setBackgroundResource(R.drawable.rounded_corner_orange_green);
                } else {
                    rating.setBackgroundResource(R.drawable.rounded_corner_grey);
                }

                rating.setText(restaurantDetailViewModel.getRestaurant().getUserRating().getAggregateRating());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_detail_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share: {
                String url = restaurantDetailViewModel.getRestaurant().getUrl();

                if (!url.equals(null)) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, url);
                Intent chooser = Intent.createChooser(share, "Share using");

                if (share.resolveActivity(Objects.requireNonNull(this).getPackageManager()) != null) {
                    startActivity(chooser);
                }}
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
