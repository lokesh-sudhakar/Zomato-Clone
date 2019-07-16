package com.example.zomatoapp.di.module;

import com.example.zomatoapp.services.RestaurantService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

//    @Provides
//    @Singleton
//    Cache provideCache(Application application) {
//        long cacheSize = 10 * 1024 * 1024; // 10 MB
//        Cache cache = new Cache(application.getCacheDir(),cacheSize);
//        return cache;
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkhttpClient(Cache cache) {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.cache(cache);
//        httpClient.connectTimeout(30, TimeUnit.SECONDS);
//        httpClient.readTimeout(30, TimeUnit.SECONDS);
//        return httpClient.build();
//    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
//  Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://developers.zomato.com")
//                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    RestaurantService provideMovieApiService(Retrofit retrofit) {
        return retrofit.create(RestaurantService.class);
    }
}