package com.cc.yweather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.yweather.R;
import com.cc.yweather.mvp.model.CityManagerInfo;

import java.util.List;

/**
 * Created by CC on 2017/7/15.
 */

public class CityManagerAdapter extends RecyclerView.Adapter<CityManagerAdapter.CityManagerViewHolder> {
    private List<CityManagerInfo> list;
    private Context context;

    public CityManagerAdapter(List<CityManagerInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CityManagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityManagerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_city_manager, parent, false));
    }

    @Override
    public void onBindViewHolder(CityManagerViewHolder holder, int position) {
        if (list == null) {
            return;
        }
        if (position == list.size()) {
            holder.tvCurrentCity.setVisibility(View.GONE); // 当前城市标签
            holder.tvArea.setVisibility(View.GONE); // 城市
            holder.tvTemperature.setVisibility(View.GONE); // 气温
            holder.tvWeather.setVisibility(View.GONE);
            holder.ivAdd.setVisibility(View.VISIBLE); // 添加图片
            holder.tvWeather.setTextSize(100);
//            holder.tvWeather.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            CityManagerInfo info = list.get(position);
            holder.tvCurrentCity.setVisibility(info.isCurrentCity() ? View.VISIBLE : View.INVISIBLE);
            holder.tvWeather.setVisibility(View.VISIBLE);
            holder.tvTemperature.setVisibility(View.VISIBLE);
            holder.tvArea.setVisibility(View.VISIBLE);
            holder.ivAdd.setVisibility(View.GONE);
            holder.tvArea.setText(info.getAreaName());
            holder.tvWeather.setText(info.getWeather());
            holder.tvTemperature.setText(info.getTemperature());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class CityManagerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvArea;
        private TextView tvWeather;
        private TextView tvTemperature;
        private TextView tvCurrentCity;
        private ImageView ivAdd;

        public CityManagerViewHolder(View itemView) {
            super(itemView);
            tvArea = (TextView) itemView.findViewById(R.id.tv_area);
            tvWeather = (TextView) itemView.findViewById(R.id.tv_weather);
            tvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            tvCurrentCity = (TextView) itemView.findViewById(R.id.tv_current_city);
            ivAdd = (ImageView) itemView.findViewById(R.id.iv_add);
        }
    }

}
