package com.cc.yweather.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Address;
import android.os.Build;
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
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cc.yweather.R;
import com.cc.yweather.app.Constance;
import com.cc.yweather.database.bean.FocusCities;
import com.cc.yweather.database.controller.CityController;
import com.cc.yweather.database.controller.WeatherController;
import com.cc.yweather.eventbus.BusCase;
import com.cc.yweather.eventbus.WeatherEvent;
import com.cc.yweather.inter.OnItemClickListener;
import com.cc.yweather.inter.OnPermissionResultListener;
import com.cc.yweather.manager.LocateManager;
import com.cc.yweather.mvp.model.DrawerMenuItem;
import com.cc.yweather.mvp.presenter.IWeatherPresenter;
import com.cc.yweather.mvp.presenter.impl.WeatherPresenterImpl;
import com.cc.yweather.mvp.view.IWeatherView;
import com.cc.yweather.ui.adapter.DrawerMenuAdapter;
import com.cc.yweather.ui.adapter.WeatherPagerAdapter;
import com.cc.yweather.ui.widget.IndicatorView;
import com.cc.yweather.util.ErrorUtils;
import com.cc.yweather.util.IntentUtils;
import com.cc.yweather.util.PermissionUtils;
import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public class WeatherActivity extends AppCompatActivity implements OnItemClickListener,
        IWeatherView, OnPermissionResultListener {

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
    @BindView(R.id.tv_area)
    TextView tvArea;

    private WeatherPagerAdapter pagerAdapter;
    private IWeatherPresenter mIWeatherPresenter;
    private Disposable mDisposable;
    private int cityCount = 0;

    @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
            getWindow().setEnterTransition(transition);
        }

        setContentView(R.layout.activity_weather);
        ImmersionBar.with(this).init(); // 沉浸式状态栏
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        PermissionUtils.permissionRequest(this, this, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        PermissionUtils.permissionRequest(this, this, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        mIWeatherPresenter = new WeatherPresenterImpl(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }

        String currentArea = CityController.getController().getCurrentCity().getArea();
        if (!TextUtils.isEmpty(currentArea)) tvArea.setText(currentArea);
        else tvArea.setText("未知地区");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        setDrawerMenu();
        if (pagerAdapter == null) {
            List<FocusCities> cities = DataSupport.findAll(FocusCities.class);
            if (cities != null && cities.size() > 0) {
                cityCount = cities.size();
                pagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(), cities);
                vpWeather.setAdapter(pagerAdapter);
                setIndicator();
            }
        }

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
        if (cityCount == 0) return;
        if (llIndicator == null) llIndicator = (LinearLayout) findViewById(R.id.ll_indicator);
        llIndicator.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
        params.gravity = Gravity.CENTER;
        for (int i = 0; i < cityCount; i++) {
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
        EventBus.getDefault().unregister(this);
        ImmersionBar.with(this).destroy();
        LocateManager.getInstance(this).stopLocation();
        LocateManager.getInstance(this).destroyLocation();
        if (mDisposable != null) mDisposable.dispose();
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
        IntentUtils.intent(this, position);
    }

    @Override
    public void getWeatherSuccess(JSONObject jsonObject, String lng, String lat) {
        WeatherController.getController().setWeather(this, jsonObject, lng, lat);
    }

    @Override
    public void getInformationFailed(ErrorUtils.ErrorMessage errorMessage) {

    }

    @Override
    public void getDisposable(Disposable d) {
        mDisposable = d;
    }

    // 定位成功
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(WeatherEvent event) {
        double longitude = event.getAMapLocation().getLongitude(); // 经度
        double latitude = event.getAMapLocation().getLatitude(); // 纬度
        mIWeatherPresenter.getWeather(Constance.APP_ID, Constance.SIGN, "5", String.valueOf(longitude),
                String.valueOf(latitude), "1", "1", "1", "1");

        // 经纬度转换成地址并保存
        Address address = LocateManager.getInstance(this).getAddressFromLat(this, latitude, longitude);
        // 数据库中保存的当前地址
        FocusCities currentCity = CityController.getController().getCurrentCity();
        // 如果定位的地址与数据库的地址不一致则提示
        if (address != null && currentCity != null) {
            String locateArea = address.getSubLocality();
            String currArea = currentCity.getArea();
            if (locateArea != null && currArea != null) {
                if (!locateArea.equals(currArea)) {
                    // 提示是否切换
                    CityController.getController().saveCity(address.getAdminArea(), address.getLocality(), address.getSubLocality(), true);
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void observer(BusCase busCase) {
        switch (busCase) {
            case LOCATE_TIMEOUT:
                Toast.makeText(this, "定位失败，请点击右上角\"+\"号手动添加城市", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void grant(String permission) {
        if (permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            LitePal.getDatabase();
        } else if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            LocateManager.getInstance(this).startLocation();
        }
    }

    @Override
    public void refused(String permissionsGroup) {
        Toast.makeText(this, "权限已拒绝，在使用中可能会存在问题", Toast.LENGTH_LONG).show();
    }

    public String getShowingArea() {
        return tvArea == null ? "青山湖区" : tvArea.getText().toString();
    }
}
