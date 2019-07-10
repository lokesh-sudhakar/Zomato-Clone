package com.example.zomatoapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.zomatoapp.model.Restaurant;

import java.util.List;

public class RestaurantListViewModel extends AndroidViewModel {
    public List<Restaurant> restaurants;
    public RestaurantListViewModel(@NonNull Application application) {
        super(application);
    }




}
