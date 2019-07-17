package com.example.zomatoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.RestaurantRvAdapter;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.viewModels.RestaurantListViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class RestaurantListFragment extends Fragment implements RestaurantRvAdapter.OnClickRestaurant{

    private RecyclerView mRecyclerView;
    private RestaurantRvAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RestaurantListViewModel viewModel;
    private int category;
    private ImageView takeAwayImage;

    ListItemClickListener listItemClickListener;

    ShimmerFrameLayout shimmerFrameLayout;


    public RestaurantListFragment(int category) {
        this.category = category;
    }

    public interface ListItemClickListener {

        void onConnectActivity(String id);
    }

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
        final View rootView = inflater.inflate(R.layout.fragment_restaurant_list_rv, container, false);
        TextView searchLayout = rootView.findViewById(R.id.search_layout);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        if(category == 5){
            takeAwayImage = rootView.findViewById(R.id.takeAwayImage);
            takeAwayImage.setVisibility(View.VISIBLE);
            Picasso.with(getContext()).load(R.drawable.take_away).transform(new RoundedCornersTransformation(15,1)).into(takeAwayImage);
        }
        shimmerFrameLayout=rootView.findViewById(R.id.shimmer_layout);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();
        mRecyclerView = rootView.findViewById(R.id.restaurant_list_rv);
        viewModel = ViewModelProviders.of(this).get(RestaurantListViewModel.class);
        viewModel.setCategory(category);
        viewModel.callNetwork(getContext());
        viewModel.getRestaurantApi().observe(this, new Observer<RestaurantApi>() {
            @Override
            public void onChanged(RestaurantApi restaurantApi) {
                if (restaurantApi == null) {
                    viewModel.setLoading(true);
                } else {
                    viewModel.setRestaurantDataList(restaurantApi.getRestaurants());
                    viewModel.setStart();
                    setAdapter();
                }
            }
        });

        return rootView;
    }

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new RestaurantRvAdapter(viewModel.getRestaurantDataList(), getContext(),this);
            mLayoutManager = new LinearLayoutManager(getContext());
            if(mAdapter.getItemCount()!=0){
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            if(mAdapter.getItemCount()!=0){
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
            mAdapter.restaurantList = viewModel.getRestaurantDataList();
            mAdapter.notifyDataSetChanged();
            viewModel.setLoading(true);
        }

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount;
                int totalItemCount;
                int pastVisibleItems;
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount && viewModel.isLoading()) {
                        viewModel.setLoading(false);
                        viewModel.callNetwork(getContext());
                    }
                }
            }
        });
    }

    @Override
    public void onPerformClick(String id) {
            listItemClickListener.onConnectActivity(id);
    }
}
