package com.example.zomatoapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantList extends AppCompatActivity implements RestaurantListFragment.ListItemClickListener{

    public static final String RESTAURANT = "restaurant_id";

    private final double LATITUDE = 12.9038;
    private final double LONGITUDE = 77.5978;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        int cuisineId = getIntent().getIntExtra("cuisine",25);
        RestaurantListFragment restaurantListFragment = new RestaurantListFragment(1,LATITUDE,LONGITUDE);
        restaurantListFragment.setCuisinesId(cuisineId);
        getSupportFragmentManager().beginTransaction().add(R.id.restaurant_list_container,restaurantListFragment)
                .commit();
    }

    @Override
    public void onConnectActivity(String id) {
        Log.d("listActivity",id);
        Intent intent= new Intent(this, RestaurantDetailActivity.class);
        intent.putExtra(RESTAURANT,id);
        startActivity(intent);
    }
}
