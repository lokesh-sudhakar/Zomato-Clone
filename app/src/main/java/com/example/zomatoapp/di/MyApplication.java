package com.example.zomatoapp.di;

import android.app.Application;

import com.example.zomatoapp.di.component.ApiComponent;
import com.example.zomatoapp.di.component.DaggerApiComponent;
import com.example.zomatoapp.di.module.ApiModule;
import com.example.zomatoapp.di.module.AppModule;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}