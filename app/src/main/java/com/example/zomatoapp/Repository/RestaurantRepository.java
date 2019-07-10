package com.example.zomatoapp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;
import com.example.zomatoapp.services.RestaurantService;
import com.example.zomatoapp.viewModels.RestaurantListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";

    private List<Restaurant> restaurants;
    private RestaurantService services;
    private int pageStart;

    public RestaurantRepository() {
        services = RetrofitRestaurantClientInstance.getRestaurantRetrofitInstance().create(RestaurantService.class);
    }

    public List<Restaurant> networkCall(int start) {
        Call<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 5008, "subzone", start, 10);
        //start second thread
        call.enqueue(new Callback<RestaurantApi>() {

            @Override
            public void onResponse(@NonNull Call<RestaurantApi> call, @NonNull Response<RestaurantApi> response) {
                if (response.isSuccessful()) {
                    restaurants = response.body().getRestaurants();
                    pageStart = response.body().getResultsStart();
                } else {
                    Log.v("NetworkCall", "failedResponse");
                }

            }

            @Override
            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
                Log.v("NetworkCall", "failed");
            }
        });
        if(start == pageStart){
            return restaurants;
        }else {
            return null;
        }
    }
}
