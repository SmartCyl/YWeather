package com.cc.yweather.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by CC on 2017/7/15.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int space;

    public SpaceItemDecoration(int spanCount, int space) {
        this.spanCount = spanCount;
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = space;
        outRect.right = space;
        if (parent.getChildAdapterPosition(view) % spanCount == 0) {
            outRect.left = space;
        }
    }
}
