package com.csoft.wing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.csoft.wing.R;
import com.csoft.wing.common.AppConstants;
import com.csoft.wing.common.ui.launcher.Launcher;
import com.csoft.wing.manager.preference.PreferenceManager;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Todo:FUll screen image size must be 1080x1920 px xxhdpi
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(PreferenceManager.getString(AppConstants.KEY_CHAT_APP_ID)) &&
                        !TextUtils.isEmpty(PreferenceManager.getString(AppConstants.KEY_USER_ID))) {
                    Launcher.launchMainActivity(SplashActivity.this, null);
                } else {
                    startActivity(new Intent(SplashActivity.this, TutorialActivity.class));
                }
            }
        }, 1000);
    }
}
