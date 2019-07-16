package com.example.zomatoapp.services;

import androidx.lifecycle.LiveData;

import com.example.zomatoapp.model.CuisinesApi;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.model.foryou.ForYouApiResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
//import rx.Observable;

public interface RestaurantService {
    @GET("/api/v2.1/{type}")
    Observable<RestaurantApi> getRestaurant(@Path("type") String cat, @Query("apikey") String key,
                                            @Query("lat") double latitude, @Query("lon") double longitude,
                                            @Query("category") int cate, @Query("start") int start,
                                            @Query("count") int num);

    @GET("/api/v2.1/search")
    Observable<RestaurantApi> getEstablishment(@Query("apikey") String key,
                                               @Query("lat") double latitude,
                                               @Query("lon") double longitude,
                                               @Query("establishment_type") int establishmentType,
                                               @Query("start") int start,@Query("count") int count);

    @GET("/api/v2.1/{type}")
    Observable<CollectionsApiResponse> getCollectionsApiResponse(@Path("type")String cat,
                                                                 @Query("apikey") String key,
                                                                 @Query("city_id") int cityId);


    @GET("/api/v2.1/{type}")
    Observable<ForYouApiResponse> getForYouApiResponse(
            @Path("type") String cat, @Query("apikey") String key,@Query("lat") double latitude,
            @Query("lon") double longitude);

    @GET("/api/v2.1/{type}")
    Call<CuisinesApi> getCuisines(@Path("type") String cat, @Query("apikey") String key,
                                    @Query("lat") double latitude, @Query("lon") double longitude);

}