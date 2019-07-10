package com.example.zomatoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.RestaurantRvAdapter;
import com.example.zomatoapp.viewModels.RestaurantListViewModel;

public class RestaurantListFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RestaurantRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RestaurantListViewModel viewModel;

    public RestaurantListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        viewModel.getRestaurant();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_restaurant_list_rv, container, false);
        mRecyclerView = rootView.findViewById(R.id.restaurant_list_rv);
        if(viewModel.restaurants != null){
            generateRestaurantList();
        }
        return rootView;
    }

    public void generateRestaurantList() {
        mAdapter.restaurantList = viewModel.restaurants;
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount;
                int totalItemCount;
                int pastVisibleItems;
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if(viewModel.getRestaurant()){
                            int numberOfItem = mAdapter.restaurantList.size();
                            mAdapter.restaurantList.addAll(viewModel.restaurants.subList(numberOfItem-1,numberOfItem+9));
                            mRecyclerView.getAdapter().notifyItemInserted(numberOfItem);
                        }
                    }

                }
            }
        });

    }

}
