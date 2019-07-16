package com.example.zomatoapp.services;

import androidx.lifecycle.LiveData;

import com.example.zomatoapp.model.CuisinesApi;
import com.example.zomatoapp.model.RestaurantApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("/api/v2.1/{type}")
    Call<RestaurantApi> getRestaurant(@Path("type") String cat, @Query("apikey") String key,
                                               @Query("lat") double latitude, @Query("lon") double longitude,
                                               @Query("category") int cate, @Query("start") int start,
                                               @Query("count") int num);

    @GET("/api/v2.1/{type}")
    Call<CuisinesApi> getCuisines(@Path("type") String cat, @Query("apikey") String key,
                                    @Query("lat") double latitude, @Query("lon") double longitude);
}