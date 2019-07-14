package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.adapter.CollectionRvAdapter;
import com.example.zomatoapp.model.collection.CollectionsApiResponse;
import com.example.zomatoapp.viewModels.CollectionsViewModel;

public class CollectionsFragment extends Fragment {

    CollectionsViewModel viewModel;
    RecyclerView collectionRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View collectionsFragment = inflater.inflate(R.layout.collections_fragment,container,
                false);
        collectionRecyclerView= collectionsFragment.findViewById(R.id.collections_list_rv);
        viewModel = ViewModelProviders.of(this).get(CollectionsViewModel.class);
       viewModel.performNetworkCall().observe(this, new Observer<CollectionsApiResponse>() {
           @Override
           public void onChanged(CollectionsApiResponse collectionsApiResponse) {
               viewModel.setCollectionsApiResponse(collectionsApiResponse);
               setAdapter();
               if (collectionsApiResponse!=null) {
                   collectionsFragment.findViewById(R.id.zomato_frame).setVisibility(View.VISIBLE);
               }
           }
       });
        return collectionsFragment;
    }

    void setAdapter(){
        if(viewModel.getCollectionsApiResponse()!=null){
            CollectionRvAdapter adapter = new CollectionRvAdapter(viewModel.getCollectionsApiResponse().
                    getCollections(), getContext());
            collectionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            collectionRecyclerView.setAdapter(adapter);
        }
    }
}
