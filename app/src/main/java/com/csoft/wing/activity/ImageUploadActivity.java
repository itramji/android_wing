package com.csoft.wing.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.csoft.wing.R;
import com.csoft.wing.common.AppConstants;
import com.csoft.wing.common.Utils;
import com.csoft.wing.common.ui.custom.views.ProgressLoader;
import com.csoft.wing.common.ui.launcher.Launcher;
import com.csoft.wing.manager.network.NetworkManager;
import com.csoft.wing.manager.network.event.Event;
import com.csoft.wing.manager.network.model.Registration;
import com.csoft.wing.manager.preference.PreferenceManager;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.Subscribe;

import static com.csoft.wing.common.AppConstants.ANDROID;
import static com.csoft.wing.common.AppConstants.KEY_CMD;
import static com.csoft.wing.common.AppConstants.KEY_COUNTRY_CODE;
import static com.csoft.wing.common.AppConstants.KEY_DEVICE_ID;
import static com.csoft.wing.common.AppConstants.KEY_MOBILE_CODE;
import static com.csoft.wing.common.AppConstants.KEY_MOBILE_NUMBER;
import static com.csoft.wing.common.AppConstants.KEY_PLATFORM;
import static com.csoft.wing.common.AppConstants.USER_LOGIN;

public class ImageUploadActivity extends BaseActivity implements View.OnClickListener {

    private static final int TAKE_PICTURE = 121;

    @Override
    protected void onStart() {
        super.onStart();
        Utils.getEventBus().register(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        iniViews();
    }

    private void iniViews() {

        findViewById(R.id.upload_image).setOnClickListener(this);
        findViewById(R.id.continue_btn).setOnClickListener(this);

        ProgressLoader progressLoader = (ProgressLoader) findViewById(R.id.progressLoader);
        progressLoader.setLoader3();

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
            case R.id.upload_image:
                Launcher.launchCameraIntent(this, TAKE_PICTURE);
                break;

            case R.id.continue_btn:
                AppCompatEditText countryEditText = (AppCompatEditText) findViewById(R.id.country);
                if (countryEditText.getText().toString().isEmpty()) {
                    showErrorMessage(getString(R.string.please_enter_name));
                } else {
                    RequestParams params = new RequestParams();
                    params.put(KEY_CMD, USER_LOGIN);
                    params.put(KEY_PLATFORM, ANDROID);
                    params.put(KEY_MOBILE_NUMBER, "8939548583");
                    params.put(KEY_MOBILE_CODE, "91");
                    params.put(KEY_COUNTRY_CODE, PreferenceManager.getString(AppConstants.COUNTRY));
                    params.put(KEY_DEVICE_ID, Utils.getDeviceId(this));

                    NetworkManager.asyncPostHTTPClient(this,
                            AppConstants.BASE_URL,
                            params,
                            Registration.class,
                            1000);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                String imageUri = Utils.getImageUri(this,
                        (Bitmap) data.getExtras().get("data"));
                break;
        }
    }

    @Subscribe
    public void onEvent(Event event) {
        switch (event.getId()) {

            case 1000:
                Log.e("TAG", "onEvent: ");
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Utils.getEventBus().unregister(this);
    }
}
