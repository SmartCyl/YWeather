package com.cc.yweather.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.cc.yweather.R;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionBar(R.color.colorPrimary);
        setToolBarTitle(R.string.about);
    }

    @SuppressLint("InlinedApi")
    @Override
    protected int getLayoutId() {
        setEnterTransition(android.R.transition.slide_top);
        return R.layout.activity_about;
    }
}
