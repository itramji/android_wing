package com.csoft.wing.adapter.item.decorator;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tringapps-admin on 6/1/17.
 */

public class StickyHeaderItemDecorator extends RecyclerView.ItemDecoration {

    private View currentView;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);
        outRect.set(0,0,0,1);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(c, recyclerView, state);
        int currentPosition = (((LinearLayoutManager) recyclerView.getLayoutManager())
                .findFirstCompletelyVisibleItemPosition());
        if (recyclerView.getAdapter().getItemViewType(currentPosition) == 1) {
            currentView = recyclerView.getLayoutManager().findViewByPosition(currentPosition);
        }

        if (currentView != null) {
            currentView.draw(c);
        }
    }

}
