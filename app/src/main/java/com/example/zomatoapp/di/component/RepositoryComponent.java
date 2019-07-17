package com.example.zomatoapp.di.component;
import com.example.zomatoapp.di.module.ApiModule;
import com.example.zomatoapp.di.module.AppModule;
import com.example.zomatoapp.repository.RestaurantRepository;
import com.example.zomatoapp.services.RestaurantService;


import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface RepositoryComponent {

    void inject(RestaurantRepository repository);
}