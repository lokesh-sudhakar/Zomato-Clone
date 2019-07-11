package com.example.zomatoapp.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantData;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;
import com.example.zomatoapp.services.RestaurantService;
import com.example.zomatoapp.viewModels.RestaurantListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    public static RestaurantRepository restaurantRepository;

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";
    private RestaurantService services;

    MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();

    public static RestaurantRepository getInstance(){
        if (restaurantRepository == null){
            restaurantRepository = new RestaurantRepository();
        }
        return restaurantRepository;
    }

    public RestaurantRepository() {
        services = RetrofitRestaurantClientInstance.getRestaurantRetrofitInstance().create(RestaurantService.class);
    }

    public MutableLiveData<RestaurantApi> networkCall(int start) {

        Call<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 5008, "subzone",1, 0, 10);
        call.enqueue(new Callback<RestaurantApi>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantApi> call, @NonNull Response<RestaurantApi> response) {
                if (response.isSuccessful()) {
                    restaurantApiMutableLiveData.setValue(response.body());
                   } else {
                    Log.v("NetworkCall", "failedResponse");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
                Log.v("NetworkCall", "failed");
                restaurantApiMutableLiveData.setValue(null);
            }
        });
        return restaurantApiMutableLiveData;
    }
}
