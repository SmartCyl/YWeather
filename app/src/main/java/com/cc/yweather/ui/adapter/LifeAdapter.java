package com.cc.yweather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.yweather.R;
import com.cc.yweather.mvp.model.LifeInfo;

import java.util.List;

/**
 * Created by CC on 2017/11/8.
 */

public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.LifeViewHolder> {
    private Context mContext;
    private List<LifeInfo> list;

    public LifeAdapter(Context context, List<LifeInfo> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public LifeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LifeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_life, parent, false));
    }

    @Override
    public void onBindViewHolder(LifeViewHolder holder, int position) {
        if (list == null) return;
        LifeInfo info = list.get(position);
        Glide.with(mContext).load(info.getIcon()).into(holder.ivLifeIcon);
        holder.tvLifeType.setText(info.getTitle());
        holder.tvLifeDesc.setText(info.getDesc());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class LifeViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivLifeIcon;
        private TextView tvLifeType;
        private TextView tvLifeDesc;

        public LifeViewHolder(View itemView) {
            super(itemView);
            ivLifeIcon = (ImageView) itemView.findViewById(R.id.iv_life_icon);
            tvLifeType = (TextView) itemView.findViewById(R.id.tv_life_type);
            tvLifeDesc = (TextView) itemView.findViewById(R.id.tv_life_desc);
        }
    }
}
