package com.example.zomatoapp.di.component;
import com.example.zomatoapp.HomeActivity;
import com.example.zomatoapp.di.module.ApiModule;
import com.example.zomatoapp.di.module.AppModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(HomeActivity activity);
}