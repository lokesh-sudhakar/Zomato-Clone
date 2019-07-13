package com.example.zomatoapp.services;

import androidx.lifecycle.LiveData;

import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {
    @GET("/api/v2.1/{type}")
    Call<RestaurantApi> getRestaurant(@Path("type") String cat, @Query("apikey") String key,
                                               @Query("entity_id") int id, @Query("entity_type") String type,
                                               @Query("category") int cate, @Query("start") int start,
                                               @Query("count") int num);

    @GET("/api/v2.1/{type}")
    Call<CollectionsApiResponse>getCollectionsApiResponse(@Path("type")String cat,@Query("apikey") String key,
                                               @Query("city_id") int cityId);

}