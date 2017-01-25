package com.csoft.wing.common.ui.custom.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csoft.wing.R;

/**
 * Created by tringapps-admin on 23/1/17.
 */

public class IndexView extends LinearLayout implements View.OnClickListener {

    private static final String[] mIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private IndexListener mListener;

    public IndexView(Context context) {
        super(context);
        init();
    }

    public IndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        this.setGravity(Gravity.CENTER_HORIZONTAL);
        for (int i = 0; i < 26; i++) {
            TextView view = new TextView(getContext());
            view.setTextSize(12f);
            view.setTextColor(getResources().getColor(R.color.black));
            view.setText(mIndex[i]);
            view.setPadding(10,0,10,0);
            view.setOnClickListener(this);
            this.addView(view);
        }

    }


    @Override
    public void onClick(View v) {
        Log.e("TAG", "onClick: "+ ((TextView) v).getText().toString());
        if (mListener != null) {
            mListener.onIndexClickListener(((TextView) v).getText().toString());
        }
    }

    public void setListerner(IndexListener listerner) {
        this.mListener = listerner;
    }


    public interface IndexListener {
        void onIndexClickListener(String character);
    }
}
