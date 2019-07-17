package com.example.zomatoapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zomatoapp.ui.order_tab_adapter.SectionsPagerAdapter;
import com.example.zomatoapp.viewModels.MapsViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class OrderFragment extends Fragment {
    private ShimmerFrameLayout mShimmerLayout;
    private TextView setLocation;
    private Context context;
    double latitude;
    double longitude;
    String address;

    public OrderFragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View orderFragmentLayout = inflater.inflate(R.layout.fragment_order_home,container,false);
        Toolbar toolbar = orderFragmentLayout.findViewById(R.id.toolbar);
        final BottomSheetDialog mBottomSheetDialog=new BottomSheetDialog(getActivity());
        final View sheetView=getActivity().getLayoutInflater().inflate(R.layout.location_bottom_sheet,null);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        setLocation= orderFragmentLayout.findViewById(R.id.title_location);
        setLocation.setTextColor(ContextCompat.getColor(context,R.color.dark_black));
        TabLayout tabLayout = getTabLayout(orderFragmentLayout);

        addLabelsToTabs(tabLayout);
        setLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View sheetView=getActivity().getLayoutInflater().inflate(R.layout.location_bottom_sheet,null);
                  mBottomSheetDialog.setContentView(sheetView);
                  mBottomSheetDialog.show();
                  sheetView.findViewById(R.id.dismissBottomSheet).setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          mBottomSheetDialog.dismiss();
                      }
                  });
                  sheetView.findViewById(R.id.useCurrentLocation).setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent intent=new Intent(getActivity(),MapsActivity.class);
                          startActivityForResult(intent,1);
                          mBottomSheetDialog.dismiss();
                      }
                  });

            }
        });
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(getContext(),"hello",Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.cancel();
                mBottomSheetDialog.dismiss();
            }
        });
        return orderFragmentLayout;
    }

    private TabLayout getTabLayout(View orderFragmentLayout) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(context,getChildFragmentManager());
        ViewPager viewPager = orderFragmentLayout.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = orderFragmentLayout.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return tabLayout;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle=getArguments();
        if(bundle!=null){
            updateLocationTextView(bundle.getString("place"));
            address=bundle.getString("place");
            latitude=bundle.getDouble("latitude");
            longitude=bundle.getDouble("longitude");
            Log.d("latitude and langitude are ",""+latitude+longitude);
        }
        else {
            LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Location location;

            if(network_enabled){

                location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if(location!=null){
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    MapsViewModel mapsViewModel=new MapsViewModel();
                    address=mapsViewModel.getAddress(latitude,longitude,getContext());
                    updateLocationTextView(address);
                }
            }
        }
    }

    public void updateLocationTextView(String location){
        TextView setLocation= getActivity().findViewById(R.id.title_location);
        setLocation.setText(location);
    }

    private void addLabelsToTabs(TabLayout tabLayout) {
        TextView deliveryTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        deliveryTab.setText(getResources().getString(R.string.delivery));
        deliveryTab.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_delivery_scooter_icon, 0);
        deliveryTab.setCompoundDrawablePadding(10);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(deliveryTab);
        TextView selfPickUpTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        selfPickUpTab.setText(getResources().getString(R.string.self_pickup));
        selfPickUpTab.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_self_pickup_paper_bag, 0);
        selfPickUpTab.setCompoundDrawablePadding(10);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(selfPickUpTab);
    }
}
