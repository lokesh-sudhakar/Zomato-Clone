package com.example.zomatoapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ZomatoGoldActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zomat_gold_activity);
        GoldFragment goldFragment=new GoldFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.containerForGoldActivity,goldFragment).commit();
    }
}
