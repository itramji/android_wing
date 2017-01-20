package com.csoft.wing.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csoft.wing.common.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class CountrySelectionAdapter extends RecyclerView.Adapter<CountrySelectionAdapter.ViewHolder> {

    private static final int HEADER = 1;
    private static final int CONTENT = 2;
    private WeakReference<Activity> mActivity;
    private JSONArray mObject;

    public CountrySelectionAdapter(Activity activity, JSONArray object) {
        mObject = object;
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setPadding(48, 48, 48, 48);
        if (viewType == HEADER) {
            textView.setBackgroundColor(Color.LTGRAY);
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            final JSONObject object = mObject.getJSONObject(position);
            holder.item.setText(object.getString("name"));
            if (getItemViewType(position) != HEADER) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra(AppConstants.COUNTRY, object.toString());
                        mActivity.get().setResult(Activity.RESULT_OK, intent);
                        mActivity.get().finish();
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return mObject != null ? mObject.length() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        try {
            return mObject.getJSONObject(position).getString("type").equals("header") ? HEADER : CONTENT;
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView;
        }
    }
}
