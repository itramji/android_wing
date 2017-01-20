package com.csoft.wing.activity;

import android.os.Bundle;
import android.view.View;

import com.csoft.wing.R;
import com.csoft.wing.common.ui.launcher.Launcher;

public class ContactPermissionActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_permission);
        initView();
    }

    private void initView() {
        findViewById(R.id.continue_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.continue_btn:
                Launcher.launchLandingScreen(this, null);
                break;
        }
    }
}
