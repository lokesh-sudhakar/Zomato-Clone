package com.example.zomatoapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zomatoapp.ForYouFragment;
import com.example.zomatoapp.R;
import com.example.zomatoapp.model.RestaurantApi;
import com.example.zomatoapp.model.RestaurantData;
import com.example.zomatoapp.model.foryou.Establishment;
import com.example.zomatoapp.viewModels.ForYouViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ForYouAdapter extends RecyclerView.Adapter<ForYouAdapter.ForYouViewHolder> {


    List<Establishment> establishments;
    Context context;
    ForYouViewModel viewModel;
    ForYouViewHolder holder_;
    int position_;
    BindCallBack bindCallBack;
    boolean iseEtablishmentsPresent = false;
    EstablishmentInnerRVAdapter.OnClickRestaurantListner onClickRestaurantListner;



    public interface BindCallBack{
        void onBindPos( List<Establishment> establishments,ForYouViewHolder holder, final int position);
    }
    public ForYouAdapter(List<Establishment> establishments, Context context,ForYouViewModel forYouViewModel,
                         EstablishmentInnerRVAdapter.OnClickRestaurantListner onClickRestaurantListner) {
        this.establishments = establishments;
        this.context = context;
        this.viewModel = forYouViewModel;
        this.onClickRestaurantListner = onClickRestaurantListner;
    }

    @NonNull
    @Override
    public ForYouViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View forYouView = LayoutInflater.from(parent.getContext()).inflate(R.layout.for_you_recycler_view_layout,
                parent, false);
        return new ForYouViewHolder(forYouView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ForYouViewHolder holder, final int position) {
        this.holder_ = holder;
        this.position_=position;
        holder_.name.setText(establishments.get(position).getEstablishment().getName());
        Log.d("lokesh name",""+establishments.get(position).getEstablishment().getName());

        viewModel.fetchEstablishment(establishments.get(position).getEstablishment().getId())
                .observe(((LifecycleOwner)context), new Observer<RestaurantApi>() {
                    @Override
                    public void onChanged(RestaurantApi restaurantApi) {
                        if (restaurantApi != null){
                            List<RestaurantData> restaurantFiltered = new ArrayList<>();
                            if(restaurantApi.getRestaurants().size()>6){
                            for (RestaurantData restaurantData: restaurantApi.getRestaurants()) {
                                if(!restaurantData.getRestaurant().getThumb().isEmpty()){
                                    restaurantFiltered.add(restaurantData);
                                }
                            }
                                Log.d("name_in ",""+establishments.get(position).getEstablishment().getName());
                                holder.establishmentLayout.setVisibility(View.VISIBLE);
                                EstablishmentInnerRVAdapter establishmentInnerRVAdapter=
                                        new EstablishmentInnerRVAdapter(restaurantFiltered,
                                                context,onClickRestaurantListner);
                                holder_.name.setText(establishments.get(position).getEstablishment().getName());
                                holder.recyclerView.setHasFixedSize(true);
                                holder.recyclerView.setItemViewCacheSize(10);
                                holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false));
                                holder.recyclerView.setAdapter(establishmentInnerRVAdapter);
                            }
                           }
                    }
                });
    }

    @Override
    public int getItemCount() {

        if(establishments.size()>6){
            return 6;
        }else{
            return establishments.size();
        }
    }

    public class ForYouViewHolder  extends  RecyclerView.ViewHolder{

        TextView name;
        RecyclerView recyclerView;
        ConstraintLayout establishmentLayout;
        public ForYouViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.establishment_name);
            recyclerView = itemView.findViewById(R.id.establishment_list_rv);
            establishmentLayout = itemView.findViewById(R.id.establishment_layout);
        }
    }
}
