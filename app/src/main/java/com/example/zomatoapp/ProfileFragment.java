package com.example.zomatoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zomatoapp.profile_tabbed_adapter.ProfileSectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public ProfileFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View profileLayoutView = inflater.inflate(R.layout.profile_fragment_with_navigation_drawer,container,false);

        //setting up toolbar
        Toolbar toolbar = profileLayoutView.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.hamburger_icon_for_navigation_drawer);

        DrawerLayout drawer = profileLayoutView.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(context,R.color.dark_black));
        toggle.syncState();

        ProfileSectionsPagerAdapter sectionsPagerAdapter = new ProfileSectionsPagerAdapter(context,getChildFragmentManager() );
        ViewPager viewPager = profileLayoutView.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = profileLayoutView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcons(tabLayout);
        return profileLayoutView;
    }

    private void setUpIcons(TabLayout tabLayout) {
        TextView dinelineTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        dinelineTab.setText("Dineline");
        tabLayout.getTabAt(0).setCustomView(dinelineTab);
        TextView reviewsTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        reviewsTab.setText("Reviews");
        tabLayout.getTabAt(1).setCustomView(reviewsTab);
        TextView photosTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        photosTab.setText("Photos");
        tabLayout.getTabAt(2).setCustomView(photosTab);
        TextView beenThereTab = (TextView) LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        beenThereTab.setText("Been There");
        tabLayout.getTabAt(3).setCustomView(beenThereTab);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.navigation_drawer, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:{
                Toast.makeText(context,"share selected",Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.notification_item:{

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
