package com.example.zomatoapp.repository;

import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;
import com.example.zomatoapp.services.RestaurantService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";
    private final String SUBZONE = "subzone";
    private final int NUM_OF_RESULT = 10;
    private RestaurantService services;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CollectionsApiResponse> collectionsApiResponseMutableLiveData =
            new MutableLiveData<>();
    private int category;



    public RestaurantRepository() {
        services = RetrofitRestaurantClientInstance.getRestaurantRetrofitInstance().
                   create(RestaurantService.class);
    }

    public MutableLiveData<CollectionsApiResponse> getCollectionsApiResponseMutableLiveData() {
        return collectionsApiResponseMutableLiveData;
    }

    public MutableLiveData<RestaurantApi> connectMutableLiveData() {
        return restaurantApiMutableLiveData;
    }

    public void networkCall(int start) {
        Call<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 5008,
                             SUBZONE, category, start, NUM_OF_RESULT);
        call.enqueue(new Callback<RestaurantApi>() {
            @Override
            public void onResponse(@NonNull Call<RestaurantApi> call,
                                   @NonNull Response<RestaurantApi> response) {
                if (response.isSuccessful()) {
                    restaurantApiMutableLiveData.setValue(response.body());
                } else {
                }
            }
            @Override
            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
                restaurantApiMutableLiveData.setValue(null);
            }
        });
    }

    public void fetchCollections() {
        Call<CollectionsApiResponse> call = services.getCollectionsApiResponse("collections",KEY,4);

        call.enqueue(new Callback<CollectionsApiResponse>() {
            @Override
            public void onResponse(Call<CollectionsApiResponse> call, Response<CollectionsApiResponse> response) {
                if (response.isSuccessful()){
                collectionsApiResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CollectionsApiResponse> call, Throwable t) {
                collectionsApiResponseMutableLiveData.setValue(null);
            }
        });
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
