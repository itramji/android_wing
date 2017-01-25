package com.csoft.wing.manager.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.csoft.wing.application.WingApplication;

/**
 * Created by tringapps-admin on 20/1/17.
 */

public class PreferenceManager {

    private static final String preferenceName = "wing";

    public static final String COUNTRY = "COUNTRY";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";

    private static SharedPreferences getSharedPreference() {
        SharedPreferences preferences = WingApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return preferences;

    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor preferences = getSharedPreference().edit();
        preferences.putBoolean(key, value);
        preferences.apply();
    }

    public static boolean getBoolean(String key) {
        SharedPreferences preferences = getSharedPreference();
        return preferences.getBoolean(key, false);
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor preferences = getSharedPreference().edit();
        preferences.putString(key, value);
        preferences.apply();
    }

    public static String getString(String key) {
        SharedPreferences preferences = getSharedPreference();
        return preferences.getString(key,"");
    }

}
