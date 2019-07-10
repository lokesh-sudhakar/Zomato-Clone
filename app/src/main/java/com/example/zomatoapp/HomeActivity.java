package com.example.zomatoapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_order:
                    Toast.makeText(getApplicationContext(),"home selected",Toast.LENGTH_SHORT).show();
                    //for testing purpose
                    OrderFragment orderFragment = new OrderFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_frame,orderFragment)
                            .commit();
                    return true;
                case R.id.navigation_go_out:
                    Toast.makeText(getApplicationContext(),"go_out selected",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_gold:
                    Toast.makeText(getApplicationContext(),"gold selected",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_search:
                    Toast.makeText(getApplicationContext(),"search selected",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_profile:
                    Toast.makeText(getApplicationContext(),"profile selected",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportActionBar().hide();
        OrderFragment orderFragment = new OrderFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame,orderFragment)
                .commit();

    }

}
