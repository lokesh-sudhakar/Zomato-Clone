package com.example.zomatoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.CuisinesRvAdapter;
import com.example.zomatoapp.model.cuisines.CuisinesApi;
import com.example.zomatoapp.viewModels.CuisinesViewModel;

public class CuisineListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CuisinesRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CuisinesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cuisines, container, false);
        mRecyclerView = rootView.findViewById(R.id.cuisines_rv);
        viewModel = ViewModelProviders.of(this).get(CuisinesViewModel.class);
        viewModel.callNetwork();
        viewModel.getCuisinesApi().observe(this, new Observer<CuisinesApi>() {
            @Override
            public void onChanged(CuisinesApi cuisinesApi) {
                if (cuisinesApi != null){
                    viewModel.setCuisinesList(cuisinesApi.getCuisines());
                    getRestaurantData();
                }
            }
        });

        return rootView;
    }
    private void getRestaurantData(){


    }

    private void setAdapter() {

    }
}
