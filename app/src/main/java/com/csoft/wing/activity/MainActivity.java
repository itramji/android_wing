package com.csoft.wing.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csoft.wing.R;
import com.csoft.wing.adapter.TabsPagerAdapter;
import com.csoft.wing.fragment.FragmentTab;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initToolbar();

        ViewPager viewPager = (ViewPager) findViewById(R.id.real_tab_content);
        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerAdapter);

        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        addTabWidget(mTabHost, "Tab 1", ContextCompat.getDrawable(this, R.drawable.fav_selector), R.layout.tab_indicator);
        addTabWidget(mTabHost, "Tab 2", ContextCompat.getDrawable(this, R.drawable.friends_selector), R.layout.tab_indicator);
        addTabWidget(mTabHost, "Tab 3", ContextCompat.getDrawable(this, R.drawable.chat_selector), R.layout.center_tab_indicator);
        addTabWidget(mTabHost, "Tab 4", ContextCompat.getDrawable(this, R.drawable.youtube_selector), R.layout.tab_indicator);
        addTabWidget(mTabHost, "Tab 5", ContextCompat.getDrawable(this, R.drawable.settings_selector), R.layout.tab_indicator);

    }

    public void addTabWidget(FragmentTabHost mTabHost, String text, Drawable background, int layoutId) {
        View view = LayoutInflater.from(this).inflate(layoutId, null);
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(text);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setBackground(background);

        mTabHost.addTab(mTabHost.newTabSpec(text).setIndicator(view),
                FragmentTab.class, null);
    }
}
