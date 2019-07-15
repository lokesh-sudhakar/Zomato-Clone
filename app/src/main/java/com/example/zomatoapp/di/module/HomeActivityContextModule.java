package com.example.zomatoapp.di.module;

import android.content.Context;

import com.example.zomatoapp.HomeActivity;
import com.example.zomatoapp.di.qualifiers.ActivityContext;
import com.example.zomatoapp.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityContextModule {

    private HomeActivity homeActivity;
    public Context context;

    public HomeActivityContextModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
        context = homeActivity;
    }

    @Provides
    @ActivityScope
    public HomeActivity providesHomeActivity() {
        return homeActivity;
    }

    @Provides
    @ActivityScope
    @ActivityContext
    public Context provideContext() {
        return context;
    }

}