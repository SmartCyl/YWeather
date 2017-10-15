package com.cc.yweather.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cc.yweather.ui.fragment.WeatherFragment;

/**
 * Created by CC on 2017/7/11.
 */

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.getInstance();
    }

    @Override
    public int getCount() {
        return 6;
    }
}
