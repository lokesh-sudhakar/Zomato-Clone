package com.example.zomatoapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRestaurantClientInstance {
        private static Retrofit retrofit;
        private static final String BASE_URL = "https://developers.zomato.com";

        public static Retrofit getRestaurantRetrofitInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();
            }
            return retrofit;
        }
}
