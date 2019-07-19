package com.example.zomatoapp;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantList extends AppCompatActivity implements RestaurantListFragment.ListItemClickListener{

    private final double LATITUDE = 12.9038;
    private final double LONGITUDE = 77.5978;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        int cuisineId = getIntent().getIntExtra("cuisine",1);
        RestaurantListFragment restaurantListFragment = new RestaurantListFragment(1,LATITUDE,LONGITUDE);
        restaurantListFragment.setCuisinesId(1);
        getSupportFragmentManager().beginTransaction().add(R.id.restaurant_list_container,restaurantListFragment)
                .commit();
    }

    @Override
    public void onConnectActivity(String id) {

    }
}
