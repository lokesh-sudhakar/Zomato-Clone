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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
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
    private double latitude;
    private double longitude;
    private String address;
    View ordrFragmentLayoutView;
    TabLayout tabLayout;

    public OrderFragment(){
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Bundle bundle = getArguments();
        if(bundle!=null){
            latitude=bundle.getDouble("latitude");
            longitude=bundle.getDouble("longitude");
//            updateLocationTextView(bundle.getString("place"));
            Log.d("networkCall","order fragment "+ latitude);
            address=bundle.getString("place");

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
//                    updateLocationTextView(address);

                }
            }
        }
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
        setLocation.setText(address);
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
                          getActivity().overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
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
        ordrFragmentLayoutView=orderFragmentLayout;
        tabLayout = getTabLayout(orderFragmentLayout);
        addLabelsToTabs(tabLayout);
        return orderFragmentLayout;
    }

    private TabLayout getTabLayout(View orderFragmentLayout) {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(context,getChildFragmentManager(),latitude,longitude);
        Log.d("latitude+longitude= ",""+latitude+" "+longitude);
        ViewPager viewPager = orderFragmentLayout.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = orderFragmentLayout.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return tabLayout;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void updateLocationTextView(String location){
        TextView setLocation= getActivity().findViewById(R.id.title_location);
        setLocation.setText(location);
//        TabLayout tabLayout = getTabLayout(onCreateView());
//      addLabelsToTabs(tabLayout);
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
