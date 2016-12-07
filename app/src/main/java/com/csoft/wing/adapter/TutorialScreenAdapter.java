package com.csoft.wing.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.csoft.wing.R;


/**
 */
public class TutorialScreenAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private TypedArray mResources;

    public TutorialScreenAdapter(Context context) {
        mResources = context.getResources().obtainTypedArray(R.array.tutorial_screens);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_tutorial_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.tutorial_image);
        imageView.setImageResource(mResources.getResourceId(position, -1));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}