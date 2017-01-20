package com.csoft.wing.application;

import android.app.Application;

/**
 * Created by tringapps-admin on 20/1/17.
 */

public class WingApplication extends Application {

    private static WingApplication mInstance;


    public static synchronized WingApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
