package com.example.zomatoapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.repository.RestaurantRepository;

public class CollectionsViewModel extends ViewModel {

    MutableLiveData<CollectionsApiResponse> collectionsApiResponseMutableLiveData =
            new MutableLiveData<>();
    private RestaurantRepository mRestaurantRepository;
    private CollectionsApiResponse collectionsApiResponse;


    public void setCollectionsApiResponse(CollectionsApiResponse collectionsApiResponse) {
        this.collectionsApiResponse = collectionsApiResponse;
    }

    public CollectionsApiResponse getCollectionsApiResponse() {
        return collectionsApiResponse;
    }



    public MutableLiveData<CollectionsApiResponse> performNetworkCall(){
        mRestaurantRepository = new RestaurantRepository();
        mRestaurantRepository.fetchCollections();
        return mRestaurantRepository.getCollectionsApiResponseMutableLiveData();
    }


}
