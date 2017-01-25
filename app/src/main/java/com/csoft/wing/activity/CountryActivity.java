package com.csoft.wing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.csoft.wing.R;
import com.csoft.wing.common.AppConstants;
import com.csoft.wing.common.ui.launcher.Launcher;
import com.csoft.wing.manager.preference.PreferenceManager;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;

import org.json.JSONException;
import org.json.JSONObject;

public class CountryActivity extends BaseActivity implements View.OnClickListener, AuthCallback {

    private AppCompatTextView countryText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        initView();
    }

    private void initView() {
        countryText = (AppCompatTextView) findViewById(R.id.country);
        findViewById(R.id.continue_btn).setOnClickListener(this);
        findViewById(R.id.country_select).setOnClickListener(this);
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
            case R.id.country_select:
                Launcher.launchCountrySelectActivityForResult(this, null, 121);
                break;

            case R.id.continue_btn:
                if (countryText.getText().toString().equals(getString(R.string.country))) {
                    showErrorMessage(getString(R.string.please_select_country));
                } else {
                    AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                            .withAuthCallBack(this)
                            .withPhoneNumber(PreferenceManager.getString(PreferenceManager.COUNTRY));

                    Digits.authenticate(authConfigBuilder.build());
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 121:
                if (resultCode == RESULT_OK) {
                    try {
                        JSONObject object = new JSONObject(data.getStringExtra(AppConstants.COUNTRY));
                        countryText.setText(object.getString("name"));
                        PreferenceManager.putString(PreferenceManager.COUNTRY, object.getString("phone"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    @Override
    public void success(DigitsSession session, String phoneNumber) {
        PreferenceManager.putString(PreferenceManager.PHONE_NUMBER, phoneNumber);
        Launcher.launchImageUpload(this, null);
    }

    @Override
    public void failure(DigitsException error) {

    }
}
