package com.example.zomatoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.network.RetrofitRestaurantClientInstance;
import com.example.zomatoapp.services.RestaurantService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantListFragment extends Fragment {

    private final String SEARCH = "search";
    private final String KEY = "17e3de8473825e5b134932479c395958";

    private RecyclerView mRecyclerView;
    //private MoviesRecyclerViewAdapter mMoviesRecyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RestaurantService services;

    public RestaurantListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_restaurant_list_rv, container, false);
        mRecyclerView = rootView.findViewById(R.id.restaurant_list_rv);
        services = RetrofitRestaurantClientInstance.getRestaurantRetrofitInstance().create(RestaurantService.class);
        networkCall(services);
        return rootView;
    }

    private void networkCall(RestaurantService service) {
        Call<RestaurantApi> call = service.getRestaurant(SEARCH, KEY,5008,"subzone", 10);
        call.enqueue(new Callback<RestaurantApi>() {

            @Override
            public void onResponse(@NonNull Call<RestaurantApi> call, @NonNull Response<RestaurantApi> response) {
                if(response.isSuccessful()){


                } else {
                    Log.v("NetworkCall", "failedResponse");
                }

            }

            @Override
            public void onFailure(@NonNull Call<RestaurantApi> call, @NonNull Throwable t) {
                Log.v("NetworkCall", "failed");
            }
        });
    }
}
