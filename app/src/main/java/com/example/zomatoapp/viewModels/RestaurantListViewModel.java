package com.example.zomatoapp.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.Repository.RestaurantRepository;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;

import java.util.List;

public class RestaurantListViewModel extends ViewModel {

    public MutableLiveData<RestaurantApi> restaurantApi;
    private RestaurantRepository mRestaurantRepository;
    private int start = 0;


    public void init(){
        if (restaurantApi != null){
            return;
        }
        mRestaurantRepository = RestaurantRepository.getInstance();
        restaurantApi = mRestaurantRepository.networkCall(0);
    }

    public MutableLiveData<RestaurantApi> getRestaurantApi() {
        return restaurantApi;
    }


//    public LiveData<RestaurantApi> getRestaurant(){
//        Log.d("RestaurantFragment","viewModel get");
//        if(mRestaurantRepository.networkCall(start) != null){
//            restaurantApi = mRestaurantRepository.networkCall(start);
//            start += 10;
//            Log.d("RestaurantFragment","viewModel gettrue");
//        }
//        Log.d("RestaurantFragment","viewModel getfalse");
//        return restaurantApi;
//    }

}
