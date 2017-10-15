package com.cc.yweather.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;

import com.cc.yweather.R;
import com.cc.yweather.inter.OnItemClickListener;
import com.cc.yweather.manager.LocateManager;
import com.cc.yweather.mvp.model.DrawerMenuItem;
import com.cc.yweather.ui.adapter.DrawerMenuAdapter;
import com.cc.yweather.ui.adapter.WeatherPagerAdapter;
import com.cc.yweather.ui.widget.IndicatorView;
import com.cc.yweather.util.IntentUtils;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends AppCompatActivity implements OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.vp_weather)
    ViewPager vpWeather;
    @BindView(R.id.ll_indicator)
    LinearLayout llIndicator;
    @BindView(R.id.rv_drawer_menu)
    RecyclerView rvDrawerMenu;

    private WeatherPagerAdapter pagerAdapter;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_weather);
        ImmersionBar.with(this).init(); // 沉浸式状态栏
        ButterKnife.bind(this);

        LocateManager.getInstance(this).startLocation();

        setIndicator();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setDrawerMenu();
        if (pagerAdapter == null)
            pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());
        vpWeather.setAdapter(pagerAdapter);

        vpWeather.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setDrawerMenu() {
        List<DrawerMenuItem> list = new ArrayList<>();
        list.add(new DrawerMenuItem(0, R.mipmap.icon_menu_city, R.string.city_manager));
        list.add(new DrawerMenuItem(1, R.mipmap.icon_menu_skin, R.string.theme_skin));
        list.add(new DrawerMenuItem(2, R.mipmap.icon_menu_settings, R.string.app_settings));
        list.add(new DrawerMenuItem(2, R.mipmap.icon_menu_share, R.string.share_weather));
        list.add(new DrawerMenuItem(2, R.mipmap.icon_menu_tool, R.string.tools));
        list.add(new DrawerMenuItem(2, R.mipmap.icon_menu_preview, R.string.bg_preview));
        list.add(new DrawerMenuItem(2, R.mipmap.icon_menu_about, R.string.about));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvDrawerMenu.setLayoutManager(manager);
        DrawerMenuAdapter adapter = new DrawerMenuAdapter(this, list);
        rvDrawerMenu.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    // 圆点指示器
    private void setIndicator() {
        if (llIndicator == null) llIndicator = (LinearLayout) findViewById(R.id.ll_indicator);
        llIndicator.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < 6; i++) {
            if (i < 5) params.rightMargin = 20;
            IndicatorView view = new IndicatorView(this);
            if (vpWeather == null) vpWeather = (ViewPager) findViewById(R.id.vp_weather);
            if (vpWeather.getCurrentItem() == i) {
                view.setSelect(true);
            }
            if (llIndicator == null) llIndicator = (LinearLayout) findViewById(R.id.ll_indicator);
            llIndicator.addView(view, params);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        ImmersionBar.with(this).destroy();
        LocateManager.getInstance(this).stopLocation();
        LocateManager.getInstance(this).destroyLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_city_manager) {
            IntentUtils.intent2ActivityAnimation(this, CityManagerActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(int position) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        switch (position) {
            case 0:
                IntentUtils.intent2ActivityAnimation(this, CityManagerActivity.class);
                break;
            case 1:
                break;
            case 2:
                IntentUtils.intent2ActivityAnimation(this, SettingsActivity.class);
                break;
            case 3:
                break;
            case 4:
                IntentUtils.intent2Activity(this, ToolListActivity.class);
                break;
            case 5:
                break;
            case 6:
                IntentUtils.intent2ActivityAnimation(this, AboutActivity.class);
                break;
        }
    }
}
