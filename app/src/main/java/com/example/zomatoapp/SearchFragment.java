package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zomatoapp.adapter.SearchRvAdapter;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;
import com.example.zomatoapp.viewModels.RestaurantListViewModel;
import java.util.List;

public class SearchFragment extends Fragment implements  SearchRvAdapter.OnClickRestaurantListner{

    private RecyclerView mRecyclerView;
    private SearchRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RestaurantListViewModel viewModel;
    private SearchView searchView;

    RestaurantListFragment.ListItemClickListener listItemClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listItemClickListener = (RestaurantListFragment.ListItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = rootView.findViewById(R.id.search_rv_list);
        searchView = rootView.findViewById(R.id.search_view);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SearchOnClick","clicked");
                searchView.setIconified(false);
            }
        });
        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        viewModel.callSearch(null);
        viewModel.getRestaurantApi().observe(this, new Observer<RestaurantApi>() {
            @Override
            public void onChanged(RestaurantApi restaurantApi) {
                if( restaurantApi != null){
                    viewModel.setRestaurantDataList(restaurantApi.getRestaurants());
                    setAdapter(restaurantApi.getRestaurants());
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("SearchOnClick","submit");
                viewModel.callSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchOnClick","text changed");
                viewModel.callSearch(newText);
                return false;
            }
        });
        return rootView;
    }

    private void setAdapter(List<RestaurantData> restaurantData) {
        if (mAdapter == null) {
            mAdapter = new SearchRvAdapter(restaurantData, getContext(),this);
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.restaurantList = restaurantData;
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickRestaurant(int id) {
        Log.d("onclick",""+id);
        listItemClickListener.onConnectActivity(String.valueOf(id));
    }
}
