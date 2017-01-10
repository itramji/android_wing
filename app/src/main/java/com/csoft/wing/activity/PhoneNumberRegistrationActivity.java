package com.csoft.wing.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.csoft.wing.R;

public class PhoneNumberRegistrationActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_registration);
        init();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.app_name));
    }

    @Override
    public void onPermissionGranted(String[] permissions, int requestCode) {

    }

    @Override
    public void onPermissionDenied() {

    }
}
