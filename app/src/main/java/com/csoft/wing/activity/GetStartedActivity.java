package com.csoft.wing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.csoft.wing.R;

public class GetStartedActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        init();
    }

    private void init() {
        findViewById(R.id.get_started).setOnClickListener(this);
    }

    @Override
    public void onPermissionGranted(String[] permissions, int requestCode) {

    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_started:
                startActivityForResult(new Intent(this, CountrySelectionActivity.class), 121);
                break;
        }
    }
}
