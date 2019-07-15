package com.example.zomatoapp.services;

import androidx.lifecycle.LiveData;

import com.example.zomatoapp.model.RestaurantApi;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("/api/v2.1/{type}")
    Observable<RestaurantApi> getRestaurant(@Path("type") String cat, @Query("apikey") String key,
                                            @Query("entity_id") int id, @Query("entity_type") String type,
                                            @Query("category") int cate, @Query("start") int start,
                                            @Query("count") int num);
}