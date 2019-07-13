package com.example.zomatoapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.repository.RestaurantRepository;
import com.example.zomatoapp.model.RestaurantApi;

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
