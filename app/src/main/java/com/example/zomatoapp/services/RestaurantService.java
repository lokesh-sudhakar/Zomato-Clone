package com.example.zomatoapp.services;

import com.example.zomatoapp.model.RestaurantApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("/api/v2.1/{category}")
    Call<RestaurantApi> getRestaurant(@Path("category") String cat, @Query("apikey") String key,
                                      @Query("entity_id") int id, @Query("entity_type") String type,
                                      @Query("count") int num);
}