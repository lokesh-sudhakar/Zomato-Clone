package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zomatoapp.go_out.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class GoOutFragment extends Fragment {

    private TextView title;
    private Context context;

    public GoOutFragment(){
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
        title =orderFragmentLayout.findViewById(R.id.title_location);
        title.setText("Dollar Layout, Phase 4, J.P.Nagar,Bengaluru");
        title.setTextColor(getResources().getColor(R.color.dark_black));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(context,getChildFragmentManager() );
        ViewPager viewPager = orderFragmentLayout.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = orderFragmentLayout.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcons(tabLayout);
        return orderFragmentLayout;
    }

    private void setUpIcons(TabLayout tabLayout) {
        TextView forYouTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        forYouTab.setText(getResources().getString(R.string.for_you));
        Objects.requireNonNull(tabLayout.getTabAt(0)).setCustomView(forYouTab);

        TextView sneakPeekTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        sneakPeekTab.setText(getResources().getString(R.string.sneak_peek));
        sneakPeekTab.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_sneak_peekoutline_videocam_24px, 0);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setCustomView(sneakPeekTab);

        TextView collectionsTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        collectionsTab.setText(getResources().getString(R.string.collections));
        Objects.requireNonNull(tabLayout.getTabAt(2)).setCustomView(collectionsTab);

        TextView eventsTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        eventsTab.setText(getString(R.string.events));
        Objects.requireNonNull(tabLayout.getTabAt(3)).setCustomView(eventsTab);

        TextView cuisinesTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        cuisinesTab.setText(getResources().getString(R.string.cuisines));
        Objects.requireNonNull(tabLayout.getTabAt(4)).setCustomView(cuisinesTab);
    }
}
