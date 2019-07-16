package com.example.zomatoapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.di.MyApplication;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.services.RestaurantService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {


    @Inject
    RestaurantService services;
    public static final String COLLECTIONS = "collections";
    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";
    private final int NUM_OF_RESULT = 10;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CollectionsApiResponse> collectionsApiResponseMutableLiveData =
            new MutableLiveData<>();
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

    public MutableLiveData<CollectionsApiResponse> getCollectionsApiResponseMutableLiveData() {
        return collectionsApiResponseMutableLiveData;
    }

    public MutableLiveData<RestaurantApi> connectMutableLiveData(){
        return restaurantApiMutableLiveData;
    }

    public void networkCall(int start) {
        Log.d("networkCall","start call" + start);
        Observable<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 12.9038,77.59,
                              category, start, NUM_OF_RESULT);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("networkCall","on disposable");
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                Log.d("networkCall","on next"+ restaurantApi.getResultsStart());
                restaurantApiMutableLiveData.setValue(restaurantApi);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("networkCall","on throwable");
                restaurantApiMutableLiveData.setValue(null);
            }

            @Override
            public void onComplete() {
                Log.d("networkCall","on complete");
            }
        });
//        call.enqueue(new Callback<RestaurantApi>() {
//            @Override
//            public void onResponse(@NonNull Call<RestaurantApi> call,
//                                   @NonNull Response<RestaurantApi> response) {
//                if (response.isSuccessful()) {
//                    Log.d("networkCall","success");
//                    restaurantApiMutableLiveData.setValue(response.body());
//                } else {
//                    Log.d("networkCall","not success");
//                }
//            }
//            @Override
//            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
//                Log.d("networkCall","failed");
//                restaurantApiMutableLiveData.setValue(null);
//            }
//        });
    }

    public void fetchCollections() {
        Call<CollectionsApiResponse> call = services.getCollectionsApiResponse(COLLECTIONS,KEY,4);

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

}
