package com.cc.yweather.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.cc.yweather.R;
import com.cc.yweather.mvp.presenter.impl.BasePresenterImpl;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by CC on 2017/7/12.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Toolbar toolbar;
    BasePresenterImpl mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.icon_back);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            toolbar.setNavigationOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    public void setImmersionBar(int res) {
        ImmersionBar.with(this).statusBarColor(res)
                .fitsSystemWindows(true)
                .navigationBarColor(res).init();
    }

    public void setImmersionBar() {
        ImmersionBar.with(this).init();
    }


    protected void setToolBarTitle(CharSequence title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        } else {
            getToolbar().setTitle(title);
            getToolbar().setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(getToolbar());
        }
    }

    protected void setToolBarTitle(int resId) {
        if (toolbar != null) {
            toolbar.setTitle(resId);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        } else {
            getToolbar().setTitle(resId);
            getToolbar().setTitleTextColor(getResources().getColor(R.color.white));
            setSupportActionBar(getToolbar());
        }
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }

    @SuppressLint("InlinedApi")
    protected void setEnterTransition(int res) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(res);
        getWindow().setEnterTransition(transition);
    }
}
