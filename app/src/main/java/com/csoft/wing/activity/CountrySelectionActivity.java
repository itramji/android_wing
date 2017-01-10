package com.csoft.wing.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csoft.wing.R;
import com.csoft.wing.adapter.CountrySelectionAdapter;

public class CountrySelectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        init();
    }

    private void init() {
        RecyclerView countryList = (RecyclerView) findViewById(R.id.country_list);
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setAdapter(new CountrySelectionAdapter());
    }

    @Override
    public void onPermissionGranted(String[] permissions, int requestCode) {

    }

    @Override
    public void onPermissionDenied() {

    }
}
