package com.csoft.wing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.csoft.wing.R;
import com.csoft.wing.common.AppConstants;
import com.csoft.wing.common.ui.launcher.Launcher;
import com.csoft.wing.manager.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

public class CountryActivity extends BaseActivity implements View.OnClickListener {

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
                    Launcher.launchImageUpload(this, null);
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
                        PreferenceManager.putString(PreferenceManager.COUNTRY, object.getString("code"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }
}
