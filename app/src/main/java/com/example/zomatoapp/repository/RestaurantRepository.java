package com.example.zomatoapp.repository;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;
import com.example.zomatoapp.services.RestaurantService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RestaurantRepository {

    public static final String COLLECTIONS = "collections";
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
        Observable<CollectionsApiResponse> collectionObservable = services.getCollectionsApiResponse(COLLECTIONS,KEY,4);

        collectionObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionsApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        collectionsApiResponseMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onNext(CollectionsApiResponse collectionsApiResponse) {
                        collectionsApiResponseMutableLiveData.setValue(collectionsApiResponse);
                    }
                });
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
