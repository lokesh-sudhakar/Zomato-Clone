package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.EstablishmentInnerRVAdapter;
import com.example.zomatoapp.adapter.ForYouAdapter;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.foryou.Establishment;
import com.example.zomatoapp.model.foryou.ForYouApiResponse;
import com.example.zomatoapp.viewModels.CollectionsViewModel;
import com.example.zomatoapp.viewModels.ForYouViewModel;

import java.util.List;

public class ForYouFragment extends Fragment implements EstablishmentInnerRVAdapter.OnClickRestaurantListner {

    ForYouViewModel viewModel;
    ForYouAdapter forYouAdapter;
    RecyclerView forYouRecyclerView;
    RestaurantListFragment.ListItemClickListener listItemClickListener;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;

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
        final View forYouFragment = inflater.inflate(R.layout.for_you_fragment,container,
                false);
        nestedScrollView=forYouFragment.findViewById(R.id.nestedView);
        progressBar=forYouFragment.findViewById(R.id.progressBar);
        forYouRecyclerView = forYouFragment.findViewById(R.id.for_you_recycler_view);
        viewModel = ViewModelProviders.of(this).get(ForYouViewModel.class);
        viewModel.performNetworkCall().observe(this, new Observer<ForYouApiResponse>() {
            @Override
            public void onChanged(ForYouApiResponse forYouApiResponse) {
                viewModel.setForYouApiResponse(forYouApiResponse);
                setAdapter();
            }
        });
        Log.d("foryouFragment","completeForYoy");
        return forYouFragment;
    }

    void setAdapter(){
        if(viewModel.getForYouApiResponse()!=null){

//            viewModel.fetchEstablishment(viewModel.getForYouApiResponse().getEstablishments().get().getEstablishment().getId())
            forYouRecyclerView.setHasFixedSize(true);
            forYouRecyclerView.setItemViewCacheSize(30);
            forYouRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            forYouAdapter = new ForYouAdapter(viewModel.getForYouApiResponse().getEstablishments(), getActivity(),viewModel,this);
             if(forYouAdapter.getItemCount()==0){
                 progressBar.setVisibility(View.VISIBLE);
                 nestedScrollView.setVisibility(View.GONE);
             }
             else {
                 progressBar.setVisibility(View.GONE);
                 nestedScrollView.setVisibility(View.VISIBLE);
             }

            forYouRecyclerView.setAdapter(forYouAdapter);

        }
    }

    @Override
    public void onClickRestaurant(int id) {
        listItemClickListener.onConnectActivity(String.valueOf(id));
    }
    //    @Override
//    public void onBindPos(final List<Establishment> establishments, final ForYouAdapter.ForYouViewHolder holder, final int position) {
//        viewModel.fetchEstablishment(establishments
//                .get(position).getEstablishment().getId())
//                .observe((this, new Observer<RestaurantApi>() {
//                    @Override
//                    public void onChanged(RestaurantApi restaurantApi) {
//                        if (restaurantApi == null) {
//                            Log.d("lokesh", "null");
//                        } else {
////                    viewModel.setRestaurantApi(restaurantApi);
//                            EstablishmentInnerRVAdapter establishmentInnerRVAdapter=
//                                    new EstablishmentInnerRVAdapter(restaurantApi.getRestaurants(),
//                                            context);
//                            //holder_.name.setText(establishments.get(position).getEstablishment().getName());
//                            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));
//                            holder.recyclerView.setAdapter(establishmentInnerRVAdapter);
//                            Log.d("lokesh name_"," "+establishments.get(position).getEstablishment().getName() +"at pos"+position);
//
//                        }
//                    }
//                });
//    }
}
