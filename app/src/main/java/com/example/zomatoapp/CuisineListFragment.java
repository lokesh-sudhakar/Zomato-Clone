package com.example.zomatoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zomatoapp.adapter.CuisinesRvAdapter;
import com.example.zomatoapp.model.cuisines_model.CuisinesList;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
public class CuisineListFragment extends Fragment implements CuisinesRvAdapter.OnClickCuisine {

    private RecyclerView mRecyclerView;
    private CuisinesRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String str;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cuisines, container, false);
        mRecyclerView = rootView.findViewById(R.id.cuisines_rv);
        str = streamTostring(getResources().openRawResource(R.raw.cuisines));
        if(!str.equals(null)){
            CuisinesList cuisinesList = new Gson().fromJson(str, CuisinesList.class);
            cuisinesList.getCuisine();
            mAdapter = new CuisinesRvAdapter(cuisinesList.getCuisine(),this.getContext(),this);
            mLayoutManager = new GridLayoutManager(this.getContext(),2);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
        return rootView;
    }

    private String streamTostring(InputStream inputStream){
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void onPerformClick(int id) {
        Intent intent = new Intent(getActivity(),RestaurantList.class);
        intent.putExtra("cuisine",id);
        startActivity(intent);
    }
}
