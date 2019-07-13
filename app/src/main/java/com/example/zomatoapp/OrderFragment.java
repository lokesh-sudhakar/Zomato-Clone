package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zomatoapp.ui.order_tab_adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class OrderFragment extends Fragment {

    private TextView title;
    private Context context;

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
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        title =orderFragmentLayout.findViewById(R.id.title_location);
        title.setText("Dollar Layout, Phase 4, J.P.Nagar,Bengaluru");
        title.setTextColor(getResources().getColor(R.color.dark_black));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(context,getChildFragmentManager());
        ViewPager viewPager = orderFragmentLayout.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = orderFragmentLayout.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcons(tabLayout);
        return orderFragmentLayout;
    }

    private void setUpIcons(TabLayout tabLayout) {
        TextView deliveryTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        deliveryTab.setText("Delivery  ");
        deliveryTab.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_delivery_scooter_icon, 0);
        tabLayout.getTabAt(0).setCustomView(deliveryTab);
        TextView selfPickUpTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        selfPickUpTab.setText("Self Pickup  ");
        selfPickUpTab.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_self_pickup_paper_bag, 0);
        tabLayout.getTabAt(1).setCustomView(selfPickUpTab);
    }
}
