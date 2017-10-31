package com.cc.yweather.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cc.yweather.database.bean.FocusCities;
import com.cc.yweather.ui.fragment.WeatherFragment;

import java.util.List;

/**
 * Created by CC on 2017/7/11.
 */

public class WeatherPagerAdapter extends FragmentStatePagerAdapter {
    private List<FocusCities> cities;

    public WeatherPagerAdapter(FragmentManager fm, List<FocusCities> cities) {
        super(fm);
        this.cities = cities;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.getInstance();
    }

    @Override
    public int getCount() {
        return cities.size();
    }
}
