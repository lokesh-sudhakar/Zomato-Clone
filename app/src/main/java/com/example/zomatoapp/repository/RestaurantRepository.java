package com.example.zomatoapp.repository;
import android.util.Log;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.R;
import com.example.zomatoapp.di.MyApplication;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.ReviewsApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.model.foryou.ForYouApiResponse;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;

import com.example.zomatoapp.services.RestaurantService;
import com.facebook.shimmer.ShimmerFrameLayout;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import rx.Scheduler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import javax.inject.Inject;



public class RestaurantRepository {


    private static final String COLLECTIONS = "collections";
    public static final String ESTABLISHMENTS = "establishments";

    @Inject
    RestaurantService services;

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";
    private final int NUM_OF_RESULT = 10;
    private final double LATTITUDE = 12.9038;
    private final double LONGITUDE = 77.5978;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CollectionsApiResponse> collectionsApiResponseMutableLiveData =
            new MutableLiveData<>();
    private MutableLiveData<Restaurant> restaurantMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<ReviewsApi> reviewsApiMutableLiveData = new MutableLiveData<>();


    private int category;

    private int longitude;
    private int latitude;
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public MutableLiveData<ReviewsApi> getReviewsApiMutableLiveData() {
        return reviewsApiMutableLiveData;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private MutableLiveData<RestaurantApi> restaurantApiLiveData = new MutableLiveData<>();

    private MutableLiveData<ForYouApiResponse> forYouApiResponseMutableLiveData = new MutableLiveData<>();

    private RestaurantApi restaurantApiResult;
    public RestaurantRepository() {
        MyApplication.getComponent().inject(this);
    }

    public RestaurantApi getRestaurantApiResult() {
        return restaurantApiResult;
    }

    public MutableLiveData<CollectionsApiResponse> getCollectionsApiResponseMutableLiveData() {
        return collectionsApiResponseMutableLiveData;
    }

    public MutableLiveData<RestaurantApi> getRestaurantApiLiveData() {
        return restaurantApiLiveData;
    }
    public MutableLiveData<Restaurant> getRestaurantMutableLiveData() {
        return restaurantMutableLiveData;
    }


    public MutableLiveData<RestaurantApi> connectMutableLiveData() {
        return restaurantApiMutableLiveData;
    }


    public MutableLiveData<ForYouApiResponse> getForYouApiResponseMutableLiveData() {
        return forYouApiResponseMutableLiveData;
    }

    public void networkCall(int start) {



        Observable<RestaurantApi> call = services.getRestaurant(SEARCH, KEY, 12.9038,77.59,

                              category, start, NUM_OF_RESULT);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("networkCall","on disposable");
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                Log.d("networkCall","on next"+ restaurantApi.getRestaurants().size());
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

    }

    public void fetchCollections() {
        Observable<CollectionsApiResponse> collectionObservable = services.getCollectionsApiResponse(COLLECTIONS,KEY,4);

        collectionObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionsApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CollectionsApiResponse collectionsApiResponse) {
                        collectionsApiResponseMutableLiveData.setValue(collectionsApiResponse);

                    }

                    @Override
                    public void onError(Throwable e) {
                         collectionsApiResponseMutableLiveData.setValue(null);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void fetchEstablishments(){
        Observable<ForYouApiResponse> forYouObservable = services.getForYouApiResponse(ESTABLISHMENTS,
                KEY,LATTITUDE,LONGITUDE);

        forYouObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForYouApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ForYouApiResponse forYouApiResponse) {
                        forYouApiResponseMutableLiveData.setValue(forYouApiResponse);
                        forYouApiResponse.getEstablishments().get(0).getEstablishment().getName();
                    }

                    @Override
                    public void onError(Throwable e) {
                        forYouApiResponseMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void fetchEstablishmentList(int id) {
        Observable<RestaurantApi> establishmentObservable= services.getEstablishment(KEY,LATTITUDE,LONGITUDE,
                id,0, 10);

        establishmentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .filter(new Predicate<RestaurantApi>() {
            @Override
            public boolean test(RestaurantApi restaurantApi) throws Exception {
                return (restaurantApi.getRestaurants().size()>5);
            }
        })
                .subscribe(new Observer<RestaurantApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RestaurantApi restaurantApi) {
                        restaurantApiLiveData.setValue(restaurantApi);
                        Log.d("error on next",""+restaurantApi.getRestaurants().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        restaurantApiLiveData.setValue(null);
                        Log.d("error on ","null in call");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void fetchRestaurantDetail(String restaurantId){
        Log.d("resid in repo",restaurantId);
        Observable<Restaurant> restaurantObservable= services.getRestaurantDetails("restaurant","5e7cc4928495f233e070022a72b7de8a",restaurantId);

        restaurantObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Restaurant>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Restaurant restaurant) {
                        restaurantMutableLiveData.setValue(restaurant);
                    }

                    @Override
                    public void onError(Throwable e) {
                        restaurantMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void fetchReviews(String id){
        Observable<ReviewsApi> reviewsApiObservable = services.getReviews(
                "reviews","5e7cc4928495f233e070022a72b7de8a",id);
        reviewsApiObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewsApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReviewsApi reviewsApi) {
                        Log.d("review","succesful");
                        reviewsApiMutableLiveData.setValue(reviewsApi);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("review","error");
                        reviewsApiMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchCall(String query){

        Observable<RestaurantApi> call = services.getSearch(SEARCH, KEY, query);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("searchCall","on disposable");
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                Log.d("searchCall","on next"+ restaurantApi.getResultsStart());
                restaurantApiMutableLiveData.setValue(restaurantApi);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("searchCall","on throwable");
                restaurantApiMutableLiveData.setValue(null);
            }

            @Override
            public void onComplete() {
                Log.d("searchCall","on complete");
            }
        });

    }

}
