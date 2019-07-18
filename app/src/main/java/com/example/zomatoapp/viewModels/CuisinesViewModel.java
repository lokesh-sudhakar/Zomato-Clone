package com.example.zomatoapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;
import com.example.zomatoapp.model.cuisines.Cuisine;
import com.example.zomatoapp.model.cuisines.CuisinesApi;
import com.example.zomatoapp.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class CuisinesViewModel extends ViewModel {
    RestaurantRepository mRestaurantRepository;

    private MutableLiveData<CuisinesApi> cuisinesApiMutableLiveData;
    private List<Cuisine> cuisines;
    private MutableLiveData<RestaurantApi> restaurantApi;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private int start = 0;
    private boolean loading = true;
    private int category;
    private double longitude;
    private double latitude;

    private final double LATTITUDE = 12.9038;
    private final double LONGITUDE = 77.5978;

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void callNetwork() {
        if (cuisinesApiMutableLiveData == null) {
            mRestaurantRepository = new RestaurantRepository();
            mRestaurantRepository.setCategory(category);
            cuisinesApiMutableLiveData = mRestaurantRepository.getCuisinesApiMutableLiveData();
            mRestaurantRepository.setLatitude(LATTITUDE);
            mRestaurantRepository.setLongitude(LONGITUDE);
            mRestaurantRepository.getCuisinesList();
        } else {
            mRestaurantRepository.getCuisinesList();
        }
    }

    public void restaurantCall(int id){
        mRestaurantRepository.getRestaurantCuisines(id);

    }
    public MutableLiveData<CuisinesApi> getCuisinesApi() {
        return cuisinesApiMutableLiveData;
    }

    public MutableLiveData<RestaurantApi> getRestaurantApi() {
        return restaurantApi;
    }

    public List<Cuisine> getCuisinesList() {
        return cuisines;
    }

    public void setCuisinesList(List<Cuisine> cuisinesList) {
            cuisines = cuisinesList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(Restaurant restaurant) {
        restaurantList.add(restaurant);
    }


}
