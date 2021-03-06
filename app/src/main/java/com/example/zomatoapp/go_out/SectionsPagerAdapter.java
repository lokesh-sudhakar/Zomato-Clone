package com.example.zomatoapp.go_out;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.zomatoapp.CollectionsFragment;
import com.example.zomatoapp.CuisineListFragment;
import com.example.zomatoapp.ForYouFragment;
import com.example.zomatoapp.R;
import com.example.zomatoapp.ui.order_tab_adapter.PlaceholderFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_1};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return new ForYouFragment();
            }
            case 2:{
                return new CollectionsFragment();
            }
            case 4:{
                return new CuisineListFragment();
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
        return 5;
    }
}