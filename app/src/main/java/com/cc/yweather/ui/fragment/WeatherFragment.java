package com.cc.yweather.ui.fragment;


import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.yweather.R;
import com.cc.yweather.database.bean.Future;
import com.cc.yweather.database.bean.Weather;
import com.cc.yweather.ui.activity.WeatherActivity;
import com.idtk.smallchart.chart.CombineChart;
import com.idtk.smallchart.data.BarData;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends Fragment {
    @BindView(R.id.chartView)
    CombineChart chartView;

    public static WeatherFragment getInstance() {
        return new WeatherFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        BarData barData = new BarData();
        ArrayList<PointF> dayTemperatures = getTemperatures(DayType.DAY);

//        if (dayTemperatures != null) {
//            ArrayList<IBarLineCurveData> dataList = new ArrayList<>();
//            barData.setValue(dayTemperatures);
//            barData.setColor(Color.CYAN);
//            barData.setPaintWidth(5);
//            barData.setTextSize(10);
//            dataList.add(barData);
//            chartView.setDataList(dataList);
//        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(getActivity()).unbind();
    }

    public ArrayList<PointF> getTemperatures(DayType type) {
        List<Weather> weathers = getWeathers();
        Log.i("getTemperatures", "weather = " + weathers);
        if (weathers == null || weathers.size() == 0) return null;
        ArrayList<PointF> points = new ArrayList<>();
        Log.i("getTemperatures", "points = " + points);
        List<Future> futures = weathers.get(0).getFutures();
        Log.i("getTemperatures", "futures = " + futures);
        if (futures == null || futures.size() == 0) return null;
        for (int i = 0; i < futures.size(); i++) {
            PointF point;
            if (type == DayType.DAY) {
                point = new PointF(i + 1, Float.valueOf(futures.get(i).getDayTemperature()));
            } else {
                point = new PointF(i + 1, Float.valueOf(futures.get(i).getNightTemperature()));
            }
            points.add(point);
        }
        return points;
    }

    private List<Weather> getWeathers() {
        Log.i("getWeathers", getActivity() + " / ");
        String showingArea = getActivity() == null ? "青山湖区" :
                ((WeatherActivity) getActivity()).getShowingArea();
        return DataSupport.where("area = ?", showingArea).find(Weather.class);
    }

    private enum DayType {
        DAY, NIGHT
    }
}
