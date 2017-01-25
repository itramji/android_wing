package com.csoft.wing.application;

import android.app.Application;

import com.csoft.wing.R;
import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by tringapps-admin on 20/1/17.
 */

public class WingApplication extends Application {

    private static WingApplication mInstance;
    private static final String TAG = "Application";

    public static synchronized WingApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.consumer_key), getString(R.string.consumer_secret_key));
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

    }

}
