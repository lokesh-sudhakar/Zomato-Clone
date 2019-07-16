package com.example.zomatoapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.di.MyApplication;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.services.RestaurantService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    @Inject
    RestaurantService services;

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";
    private final int NUM_OF_RESULT = 10;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();
    private int category;
    private int longitude;
    private int latitude;

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public RestaurantRepository() {
        MyApplication.getComponent().inject(this);
    }

    public MutableLiveData<RestaurantApi> connectMutableLiveData() {
        return restaurantApiMutableLiveData;
    }

    public void networkCall(int start) {
        Call<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 12.9038,
                             77.5978, category, start, NUM_OF_RESULT);
        Log.d("networkCall", "start" + start);
        call.enqueue(new Callback<RestaurantApi>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantApi> call,
                                   @NonNull Response<RestaurantApi> response) {
                if (response.isSuccessful()) {
                    restaurantApiMutableLiveData.setValue(response.body());
                    Log.d("networkCall", "response success"+response.body().getResultsStart());
                } else {
                    Log.d("networkCall", "response failed");
                }
            }
            @Override
            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
                restaurantApiMutableLiveData.setValue(null);
                Log.d("networkCall", "no response");
            }
        });
    }
}
