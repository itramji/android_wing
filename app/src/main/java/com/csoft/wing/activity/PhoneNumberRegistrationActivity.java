package com.csoft.wing.activity;

import android.os.Bundle;

import com.csoft.wing.R;

public class PhoneNumberRegistrationActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_registration);
        init();
    }

    private void init() {
        initToolbar();
        setToolbarTitle(getString(R.string.app_name));
    }

    @Override
    public void onPermissionGranted(String[] permissions, int requestCode) {

    }

    @Override
    public void onPermissionDenied() {

    }
}
