package com.csoft.wing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csoft.wing.R;
import com.csoft.wing.adapter.TutorialScreenAdapter;


public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);

        TutorialScreenAdapter mCustomPagerAdapter = new TutorialScreenAdapter(this);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.tutorial_pager);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.view_indicator);

        mViewPager.setAdapter(mCustomPagerAdapter);

        for (int i = 0; i < mCustomPagerAdapter.getCount(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setButtonDrawable(ContextCompat.getDrawable(this, R.drawable.custom_radio_button));
            radioButton.setPadding(10, 0, 10, 0);
            radioGroup.addView(radioButton);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        findViewById(R.id.skip_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, GetStartedActivity.class));
    }


}
