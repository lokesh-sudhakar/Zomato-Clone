package com.example.zomatoapp.di.module;

import android.app.Application;

import com.example.zomatoapp.di.scopes.ApplicationScope;
import com.example.zomatoapp.services.RestaurantService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {


    @Provides
    @ApplicationScope
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @ApplicationScope
    @Provides
    @Singleton
    Cache provideCache(Application application) {
        long cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(application.getCacheDir(),cacheSize);
//        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
//        return new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }

    @ApplicationScope
    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.cache(cache);
//        httpClient.addInterceptor(logging);
//        httpClient.addNetworkInterceptor(new RequestInterceptor());
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    @ApplicationScope
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://developers.zomato.com")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @ApplicationScope
    @Singleton
    RestaurantService provideMovieApiService(Retrofit retrofit) {
        return retrofit.create(RestaurantService.class);
    }
}