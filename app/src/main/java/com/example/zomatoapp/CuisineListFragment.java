package com.example.zomatoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.CuisinesRvAdapter;

public class CuisineListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private CuisinesRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cuisines, container, false);
        mRecyclerView = rootView.findViewById(R.id.cuisines_rv);
        return rootView;
    }
    private void setAdapter() {

    }
}
