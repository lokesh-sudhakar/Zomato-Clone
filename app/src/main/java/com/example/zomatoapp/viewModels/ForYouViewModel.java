package com.example.zomatoapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.foryou.ForYouApiResponse;
import com.example.zomatoapp.repository.RestaurantRepository;

public class ForYouViewModel extends ViewModel {

    private RestaurantRepository mRestaurantRepository;
    private ForYouApiResponse forYouApiResponse;
    RestaurantApi restaurantApi;
    private MutableLiveData<RestaurantApi> restaurantApiMutableLiveData = new MutableLiveData<>();

    public ForYouApiResponse getForYouApiResponse() {
        return forYouApiResponse;
    }

    public void setForYouApiResponse(ForYouApiResponse forYouApiResponse){
        this.forYouApiResponse = forYouApiResponse;
    }

    public RestaurantApi getRestaurantApi() {
        return restaurantApi;
    }

    public void setRestaurantApi(RestaurantApi restaurantApi) {
        this.restaurantApi = restaurantApi;
    }

    public MutableLiveData<RestaurantApi> getRestaurantApiMutableLiveData() {
        return restaurantApiMutableLiveData;
    }

    public MutableLiveData<ForYouApiResponse> performNetworkCall(){
        mRestaurantRepository = new RestaurantRepository();
        mRestaurantRepository.fetchEstablishments();
        return mRestaurantRepository.getForYouApiResponseMutableLiveData();
    }

    public MutableLiveData<RestaurantApi> fetchEstablishment(int id){
        mRestaurantRepository = new RestaurantRepository();
        mRestaurantRepository.fetchEstablishmentList(id);
        return mRestaurantRepository.getRestaurantApiLiveData();
    }
}
