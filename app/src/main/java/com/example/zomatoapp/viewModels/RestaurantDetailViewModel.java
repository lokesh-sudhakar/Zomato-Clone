package com.example.zomatoapp.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zomatoapp.model.Restaurant;
import com.example.zomatoapp.model.ReviewsApi;
import com.example.zomatoapp.repository.RestaurantRepository;

public class RestaurantDetailViewModel extends ViewModel {

    MutableLiveData<Restaurant> restaurantMutableLiveData =
            new MutableLiveData<>();
    RestaurantRepository mRestaurantRepository;

    public ReviewsApi getReviewsApi() {
        return reviewsApi;
    }

    public void setReviewsApi(ReviewsApi reviewsApi) {
        this.reviewsApi = reviewsApi;
    }

    ReviewsApi reviewsApi;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    Restaurant restaurant;

    public MutableLiveData<Restaurant> fetchRestaurantDetails(String restaurantId){
        mRestaurantRepository = new RestaurantRepository();
        mRestaurantRepository.fetchRestaurantDetail(restaurantId);
        return mRestaurantRepository.getRestaurantMutableLiveData();
    }


    public MutableLiveData<ReviewsApi> fetchReview(String id){
        mRestaurantRepository=new RestaurantRepository();
        mRestaurantRepository.fetchReviews(id);
        return mRestaurantRepository.getReviewsApiMutableLiveData();
    }
}
