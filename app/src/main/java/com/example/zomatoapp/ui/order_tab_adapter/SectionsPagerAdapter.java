package com.example.zomatoapp.ui.order_tab_adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.zomatoapp.R;
import com.example.zomatoapp.RestaurantListFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    double latitude;
    double longitude;

    public SectionsPagerAdapter(Context context, FragmentManager fm, double lat, double lon) {
        super(fm);
        Log.d("latitude+longitudeInPager",""+lat+" "+lon);
        mContext = context;
        latitude = lat;
        longitude = lon;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:{
                return new RestaurantListFragment(1,latitude,longitude);
            }
            case 1:{
                return new RestaurantListFragment(5,latitude,longitude);
            }

        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}