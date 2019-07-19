package com.example.zomatoapp.repository;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.lifecycle.MutableLiveData;

import com.example.zomatoapp.di.MyApplication;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.ReviewsApi;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.model.foryou.ForYouApiResponse;

import com.example.zomatoapp.services.RestaurantService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


import javax.inject.Inject;



public class RestaurantRepository {


    private static final String COLLECTIONS = "collections";
    public static final String ESTABLISHMENTS = "establishments";

    private List<String> keyList = new ArrayList<>();
    @Inject
    RestaurantService services;

    private final String SEARCH = "search";
    private final String KEY_ONE = "17e3de8473825e5b134932479c395958";
    private final String KEY_Two = "5e7cc4928495f233e070022a72b7de8a";
    private final String KEY_THREE = "7297a1c8f92f9eb0b41e6007dffbf78e";
    private final int NUM_OF_RESULT = 10;
    private final double LATTITUDE = 12.9038;
    private final double LONGITUDE = 77.5978;

    private String key = KEY_Two;
    private int cuisinesId = 0;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<CollectionsApiResponse> collectionsApiResponseMutableLiveData =
            new MutableLiveData<>();
    private MutableLiveData<Restaurant> restaurantMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<ReviewsApi> reviewsApiMutableLiveData = new MutableLiveData<>();

    private int category;
    private double longitude;
    private double latitude;

    public RestaurantRepository() {
        MyApplication.getComponent().inject(this);
        keyList.add(KEY_ONE);
        keyList.add(KEY_Two);
        keyList.add(KEY_THREE);
    }

    public void setLongitude(double longitude) {
        DecimalFormat df = new DecimalFormat("#.####");
        this.longitude = Double.valueOf(df.format(longitude));
        Log.d("Repository","longitude-"+this.longitude);
    }

    public void setLatitude(double latitude) {
        DecimalFormat df = new DecimalFormat("#.####");
        this.latitude = Double.valueOf(df.format(latitude));
        Log.d("Repository"," latitude"+this.latitude);

    }

    public void setCuisinesId(int cuisinesId) { this.cuisinesId = cuisinesId; }

    public MutableLiveData<ReviewsApi> getReviewsApiMutableLiveData() {
        return reviewsApiMutableLiveData;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private MutableLiveData<RestaurantApi> restaurantApiLiveData = new MutableLiveData<>();

    private MutableLiveData<ForYouApiResponse> forYouApiResponseMutableLiveData = new MutableLiveData<>();

    private RestaurantApi restaurantApiResult;

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

    public void networkCall(final int start) {

        Observable<RestaurantApi> call;
        if(cuisinesId != 0){
            call = services.getRestaurantByCuisines(SEARCH,key,latitude,longitude,cuisinesId,start,NUM_OF_RESULT);
            Log.d("networkCall","cuisine" + "-" + latitude+"-" + longitude +" key =" + keyList.indexOf(key));
        } else{
            call = services.getRestaurant(SEARCH, key, latitude,longitude, category, start, NUM_OF_RESULT);
            Log.d("networkCall","not cuisine" + "-" + latitude+"-" + longitude +" key =" + keyList.indexOf(key));
        }
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d("networkCall","on disposable" + category+"-" + start + "-" + latitude+"-" + longitude);
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                Log.d("networkCall","on next" + category+"-" + start+ "-" + latitude+"-" + longitude);
                restaurantApiMutableLiveData.setValue(restaurantApi);
            }

            @Override
            public void onError(Throwable e) {
                restaurantApiMutableLiveData.setValue(null);
                int pos = keyList.indexOf(key);
                pos++;
                pos = pos%3;
                key=keyList.get(pos);
                Log.d("networkCall","on Error" + category +"-"+ start+ e.getMessage() + "-" + latitude+"-" + longitude);
            }

            @Override
            public void onComplete() {
                Log.d("networkCall","on complete" + category +"-"+ start+ "-" + latitude+"-" + longitude);
            }
        });

    }

    public void fetchCollections() {
        Observable<CollectionsApiResponse> collectionObservable = services.getCollectionsApiResponse(COLLECTIONS,key,4);

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
                        int pos = keyList.indexOf(key);
                        pos++;
                        pos = pos%3;
                        key=keyList.get(pos);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void fetchEstablishments(){
        Log.d("foryou","start");
        Observable<ForYouApiResponse> forYouObservable = services.getForYouApiResponse(ESTABLISHMENTS,
                key,latitude,longitude);

        forYouObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ForYouApiResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("foryou","subscribe");

                    }

                    @Override
                    public void onNext(ForYouApiResponse forYouApiResponse) {
                        forYouApiResponseMutableLiveData.setValue(forYouApiResponse);
                        forYouApiResponse.getEstablishments().get(0).getEstablishment().getName();
                        Log.d("foryou","next"+ forYouApiResponse.getEstablishments().get(0).getEstablishment().getName() + forYouApiResponse.getEstablishments().size());

                    }

                    @Override
                    public void onError(Throwable e) {
                        forYouApiResponseMutableLiveData.setValue(null);
                        int pos = keyList.indexOf(key);
                        pos++;
                        pos = pos%3;
                        key=keyList.get(pos);
                        Log.d("foryou","error");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("foryou","complete");

                    }
                });

    }

    public void fetchEstablishmentList(int id) {
        Log.d("foryou1","start");

        Observable<RestaurantApi> establishmentObservable= services.getEstablishment(key,12.9038,77.5978,
                id,0, 10);

        establishmentObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//        .filter(new Predicate<RestaurantApi>() {
//            @Override
//            public boolean test(RestaurantApi restaurantApi) throws Exception {
//                return (restaurantApi.getRestaurants().size()>5);
//            }
//        })
                .subscribe(new Observer<RestaurantApi>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("foryou1","subscribe");

                    }

                    @Override
                    public void onNext(RestaurantApi restaurantApi) {
                        restaurantApiLiveData.setValue(restaurantApi);
                        Log.d("foryou1","next "+restaurantApi.getRestaurants().size());

                    }

                    @Override
                    public void onError(Throwable e) {
                        restaurantApiLiveData.setValue(null);
                        int pos = keyList.indexOf(key);
                        pos++;
                        pos = pos%3;
                        key=keyList.get(pos);
                        Log.d("foryou1","error");

                        Log.d("error on ","null in call");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("foryou1","complete");

                    }
                });
    }

    public void fetchRestaurantDetail(String restaurantId){
        Log.d("resid in repo",restaurantId);
        Observable<Restaurant> restaurantObservable= services.getRestaurantDetails("restaurant",key,restaurantId);

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
                        int pos = keyList.indexOf(key);
                        pos++;
                        pos = pos%3;
                        key=keyList.get(pos);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void fetchReviews(String id){
        Observable<ReviewsApi> reviewsApiObservable = services.getReviews(
                "reviews",key,id,0,20);
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
                        int pos = keyList.indexOf(key);
                        pos++;
                        pos = pos%3;
                        key=keyList.get(pos);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchCall(String query){

        Observable<RestaurantApi> call = services.getSearch(SEARCH, key, query);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("searchCall","on disposable");
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                Log.d("searchCall","on next"+ restaurantApi.getResultsStart());
                if(restaurantApi.getResultsFound()>0){
                    restaurantApiMutableLiveData.setValue(restaurantApi);
                }
                restaurantApiMutableLiveData.setValue(null);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("searchCall","on throwable");
                restaurantApiMutableLiveData.setValue(null);
                int pos = keyList.indexOf(key);
                pos++;
                pos = pos%3;
                key=keyList.get(pos);
            }

            @Override
            public void onComplete() {
                Log.d("searchCall","on complete");
            }
        });

    }

    public void getRestaurantCuisines(int id){

        Observable<RestaurantApi> call = services.getRestaurantByCuisines(SEARCH, key, latitude,longitude,id,0,1);

        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RestaurantApi>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(RestaurantApi restaurantApi) {
                restaurantApiLiveData.setValue(restaurantApi);
            }

            @Override
            public void onError(Throwable e) {
                restaurantApiMutableLiveData.setValue(null);
                int pos = keyList.indexOf(key);
                pos++;
                pos = pos%3;
                key=keyList.get(pos);
            }

            @Override
            public void onComplete() {

            }
        });



    }

}
