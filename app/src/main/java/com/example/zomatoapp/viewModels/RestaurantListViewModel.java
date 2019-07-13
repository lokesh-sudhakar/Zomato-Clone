package com.example.zomatoapp.viewModels;


import android.app.Application;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.zomatoapp.model.Restaurant;

import com.example.zomatoapp.repository.RestaurantRepository;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;


import com.example.zomatoapp.repository.RestaurantRepository;
import com.example.zomatoapp.model.RestaurantApi;

import java.util.List;

public class RestaurantListViewModel extends ViewModel {

    private MutableLiveData<RestaurantApi> restaurantApi;
    private RestaurantRepository mRestaurantRepository;
    private List<RestaurantData> restaurantDataList;
    private int start = 0;
    private boolean loading = true;
    private int category;


    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void callNetwork() {
        if (restaurantApi == null) {
            mRestaurantRepository = new RestaurantRepository(category);
            restaurantApi = mRestaurantRepository.connectMutableLiveData();
            mRestaurantRepository.networkCall(start);
        } else {
            mRestaurantRepository.networkCall(start);
        }
    }

    public MutableLiveData<RestaurantApi> getRestaurantApi() {
        return restaurantApi;
    }

    public List<RestaurantData> getRestaurantDataList() {
        return restaurantDataList;
    }

    public void setRestaurantDataList(List<RestaurantData> restaurantData) {
        if (restaurantDataList == null) {
            restaurantDataList = restaurantData;
        } else {
            restaurantDataList.addAll(restaurantData);
        }
    }

    public void setStart() {
        start += 10;
    }
}
