package com.cc.yweather.ui.activity;

import android.os.Bundle;

import com.cc.yweather.R;

public class ToolListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmersionBar(R.color.colorPrimary);
        setToolBarTitle(R.string.tools);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tool_list;
    }
}
