package com.example.mynewsfeed.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mynewsfeed.Feed2;
import com.example.mynewsfeed.Fragment1;
import com.example.mynewsfeed.Fragment2;
import com.example.mynewsfeed.Fragment3;
import com.example.mynewsfeed.NewsListFragment;
import com.example.mynewsfeed.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = NewsListFragment.newInstance("us", "technology"); //enviamos url hacia NewsListFragment
                break;
            case 1:
                fragment = NewsListFragment.newInstance("mx", "technology");
                break;
            case 2:
                fragment = NewsListFragment.newInstance("au", "technology");
                break;
        }
        return fragment;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}