package com.cc.yweather.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.cc.yweather.R;
import com.cc.yweather.mvp.model.CityManagerInfo;
import com.cc.yweather.ui.adapter.CityManagerAdapter;
import com.cc.yweather.ui.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 城市管理
 * Created by CC on 2017/7/12.
 */
public class CityManagerActivity extends BaseActivity {
    @BindView(R.id.rv_city_manager)
    RecyclerView rvCityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionBar();
        setToolBarTitle(R.string.city_manager);
        ButterKnife.bind(this);

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvCityManager.setLayoutManager(manager);
        rvCityManager.addItemDecoration(new SpaceItemDecoration(3, 20));

        List<CityManagerInfo> list = new ArrayList<>();
        list.add(new CityManagerInfo("北京", "晴", "35°C", false));
        list.add(new CityManagerInfo("南昌", "晴", "32°C", true));
        list.add(new CityManagerInfo("合肥", "晴", "33°C", false));
        list.add(new CityManagerInfo("深圳", "雨", "28°C", false));

        CityManagerAdapter adapter = new CityManagerAdapter(list, this);
        rvCityManager.setAdapter(adapter);
    }

    @SuppressLint("InlinedApi")
    @Override
    protected int getLayoutId() {
        setEnterTransition(android.R.transition.slide_bottom);
        return R.layout.activity_city_manager;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_city_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_modify_skin) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }
}
