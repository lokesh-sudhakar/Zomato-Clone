package com.example.zomatoapp.viewModels;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.zomatoapp.repository.RestaurantRepository;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;
import java.util.List;

import javax.inject.Inject;

public class RestaurantListViewModel extends ViewModel {

    RestaurantRepository mRestaurantRepository;

    private MutableLiveData<RestaurantApi> restaurantApi;
    private List<RestaurantData> restaurantDataList;
    private int start = 0;
    private boolean loading = true;
    private int category;

    public void callNetwork() {
        if (restaurantApi == null) {
            mRestaurantRepository = new RestaurantRepository();
            restaurantApi = mRestaurantRepository.connectMutableLiveData();
            mRestaurantRepository.setCategory(1);
            mRestaurantRepository.networkCall(start);
        } else {
            mRestaurantRepository.networkCall(start);
        }
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public MutableLiveData<RestaurantApi> getRestaurantApi() {
        return restaurantApi;
    }

    public List<RestaurantData> getRestaurantDataList() {
        return restaurantDataList;
    }

    public void setRestaurantDataList(List<RestaurantData> restaurantData) {
        if (restaurantDataList == null) {
            restaurantDataList = restaurantData;
        } else {
            restaurantDataList.addAll(restaurantData);
        }
    }

    public void setStart() {
        start += 10;
    }
}
