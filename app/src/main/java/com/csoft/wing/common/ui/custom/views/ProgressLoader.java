package com.csoft.wing.common.ui.custom.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.csoft.wing.R;

/**
 * Created by tringapps-admin on 18/1/17.
 */

public class ProgressLoader extends RelativeLayout {

    private View loader1, loader2, loader3;

    public ProgressLoader(Context context) {
        super(context);
        init();
    }

    public ProgressLoader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressLoader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.loader_inflate, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loader1 = findViewById(R.id.loader1);
        loader2 = findViewById(R.id.loader2);
        loader3 = findViewById(R.id.loader3);
    }

    public void setLoader1(){
        loader1.setBackground(getResources().getDrawable(R.drawable.completed_circle));
        loader2.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
        loader3.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
    }

    public void setLoader2(){
        loader1.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
        loader2.setBackground(getResources().getDrawable(R.drawable.completed_circle));
        loader3.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
    }

    public void setLoader3(){
        loader1.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
        loader2.setBackground(getResources().getDrawable(R.drawable.un_completed_circle));
        loader3.setBackground(getResources().getDrawable(R.drawable.completed_circle));
    }
}
