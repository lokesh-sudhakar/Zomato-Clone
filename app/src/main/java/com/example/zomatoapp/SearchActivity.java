package com.example.zomatoapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity implements RestaurantListFragment.ListItemClickListener {

    public static final String RESTAURANT = "restaurant_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchFragment searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.search_container,searchFragment)
                .commit();
    }

    @Override
    public void onConnectActivity(String id) {
        Log.d("searchActivity",id);
        Intent intent= new Intent(this, RestaurantDetailActivity.class);
        intent.putExtra(RESTAURANT,id);
        startActivity(intent);
    }

}
