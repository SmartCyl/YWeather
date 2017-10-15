package com.cc.yweather.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cc.yweather.R;
import com.cc.yweather.inter.OnItemClickListener;
import com.cc.yweather.mvp.model.DrawerMenuItem;

import java.util.List;

/**
 * Created by CC on 2017/7/13.
 * 主页侧滑菜单列表
 */

public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.MenuViewHolder> {

    private List<DrawerMenuItem> list;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public DrawerMenuAdapter(Context context, List<DrawerMenuItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_drawer_menu, parent, false));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, final int position) {
        DrawerMenuItem item = list.get(position);
        holder.item_icon.setImageResource(item.getIcon());
//        holder.item_icon.setColorFilter(context.getResources().getColor(R.color.black));
        holder.item_title.setText(item.getResId());

        holder.ll_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_icon;
        private TextView item_title;
        private LinearLayout ll_item_container;

        public MenuViewHolder(View itemView) {
            super(itemView);
            item_icon = (ImageView) itemView.findViewById(R.id.item_icon);
            item_title = (TextView) itemView.findViewById(R.id.item_title);
            ll_item_container = (LinearLayout) itemView.findViewById(R.id.ll_item_container);
        }
    }
}
