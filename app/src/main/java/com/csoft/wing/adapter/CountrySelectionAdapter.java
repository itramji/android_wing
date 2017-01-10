package com.csoft.wing.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class CountrySelectionAdapter extends RecyclerView.Adapter<CountrySelectionAdapter.ViewHolder> {

    private static final int HEADER = 1;
    private static final int CONTENT = 2;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        if (viewType == HEADER) {
            textView.setBackgroundColor(Color.LTGRAY);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case HEADER:
                holder.item.setText("Header"+position);
                break;

            case CONTENT:
                holder.item.setText("Content"+position);
                break;
        }

    }


    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 10 == 0 ? HEADER : CONTENT;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView;
        }
    }
}
