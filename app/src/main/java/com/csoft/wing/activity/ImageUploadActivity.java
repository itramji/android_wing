package com.csoft.wing.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.csoft.wing.R;
import com.csoft.wing.common.AppConstants;
import com.csoft.wing.common.Utils;
import com.csoft.wing.common.ui.custom.views.GlideImageView;
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

    private static final int GALLERY_PICTURE = 121;

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

        findViewById(R.id.profile_image).setOnClickListener(this);
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
            case R.id.profile_image:
                Launcher.lauchGalleryIntent(this, GALLERY_PICTURE);
                break;

            case R.id.continue_btn:
                AppCompatEditText countryEditText = (AppCompatEditText) findViewById(R.id.country);
                if (countryEditText.getText().toString().isEmpty()) {
                    showErrorMessage(getString(R.string.please_enter_name));
                } else if (!Utils.isNetworkAvailable(this)) {
                    showErrorMessage(getString(R.string.network_error));
                } else {
                    String phoneNumber = PreferenceManager.getString(PreferenceManager.PHONE_NUMBER);
                    RequestParams params = new RequestParams();
                    params.put(KEY_CMD, USER_LOGIN);
                    params.put(KEY_PLATFORM, ANDROID);
                    params.put(KEY_MOBILE_NUMBER, phoneNumber.substring(3, phoneNumber.length()));
                    params.put(KEY_MOBILE_CODE, phoneNumber.substring(0,2));
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
            case GALLERY_PICTURE:
                Uri uri = data.getData();
                if (data != null) {
                    GlideImageView profileImage = (GlideImageView) findViewById(R.id.profile_image);
                    profileImage.loadRoundImage(uri);
                }
                break;
        }
    }

    @Subscribe
    public void onEvent(Event event) {
        switch (event.getId()) {

            case 1000:
                Log.e("TAG", "onEvent: " + event.toString());
                Registration registration = (Registration) event.getObject();
                if (registration != null) {
                    PreferenceManager.putString(AppConstants.KEY_CHAT_APP_ID, registration.getChatappId());
                    PreferenceManager.putString(AppConstants.KEY_USER_ID, registration.getChatappId());
                }
                Launcher.launchPermissionScreen(this, null);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Utils.getEventBus().unregister(this);
    }
}
