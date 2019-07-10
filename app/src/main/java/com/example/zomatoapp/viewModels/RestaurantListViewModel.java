package com.example.zomatoapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.zomatoapp.Repository.RestaurantRepository;
import com.example.zomatoapp.model.Restaurant;

import java.util.List;

public class RestaurantListViewModel extends AndroidViewModel {

    public List<Restaurant> restaurants;
    private RestaurantRepository mRestaurantRepository;
    private int start = 0;

    public RestaurantListViewModel(@NonNull Application application) {
        super(application);
        mRestaurantRepository = new RestaurantRepository();
    }

    public boolean getRestaurant(){
        if(mRestaurantRepository.networkCall(start) != null){
            restaurants.addAll(start,mRestaurantRepository.networkCall(start));
            start += 10;
            return true;
        }
        return false;
    }




}
