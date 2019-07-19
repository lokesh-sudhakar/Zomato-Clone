package com.example.zomatoapp.di;

import android.app.Application;

import com.example.zomatoapp.di.component.DaggerRepositoryComponent;
import com.example.zomatoapp.di.component.RepositoryComponent;
import com.example.zomatoapp.di.module.ApiModule;
import com.example.zomatoapp.di.module.AppModule;

public class MyApplication extends Application {

    private static RepositoryComponent repositoryComponent;

    public static RepositoryComponent getComponent() {
        return repositoryComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        repositoryComponent = DaggerRepositoryComponent.builder().appModule(new AppModule(this))
                .apiModule(new ApiModule()).build();
    }
}