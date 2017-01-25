package com.csoft.wing.common.ui.launcher;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

import com.csoft.wing.activity.ContactPermissionActivity;
import com.csoft.wing.activity.CountrySelectionActivity;
import com.csoft.wing.activity.ImageUploadActivity;
import com.csoft.wing.activity.MainActivity;

/**
 * Created by tringapps-admin on 20/1/17.
 */

public class Launcher {

    public static void launchImageUpload(Activity activity, Intent intent) {
        Intent lIntent = new Intent(activity, ImageUploadActivity.class);
        if (intent != null) {
            lIntent.putExtras(intent);
        }
        activity.startActivity(lIntent);
    }

    public static void launchCountrySelectActivityForResult(Activity activity,
                                                            Intent intent,
                                                            int requestCode) {
        Intent lIntent = new Intent(activity, CountrySelectionActivity.class);
        if (intent != null) {
            lIntent.putExtras(intent);
        }
        activity.startActivityForResult(lIntent, requestCode);
    }

    public static void launchCameraIntent(Activity activity, int requestCode) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, requestCode);
    }

    public static void lauchGalleryIntent(Activity activity, int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(galleryIntent, requestCode);
    }

    public static void launchPermissionScreen(Activity activity, Intent intent) {
        Intent lIntent = new Intent(activity, ContactPermissionActivity.class);
        if (intent != null) {
            lIntent.putExtras(intent);
        }
        activity.startActivity(lIntent);
    }

    public static void launchLandingScreen(Activity activity, Intent intent) {
        Intent lIntent = new Intent(activity, MainActivity.class);
        if (intent != null) {
            lIntent.putExtras(intent);
        }
        lIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        lIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(lIntent);
    }

    public static void launchMainActivity(Activity activity, Intent intent){
        Intent lIntent = new Intent(activity, MainActivity.class);
        if (intent != null) {
            lIntent.putExtras(intent);
        }
        activity.startActivity(lIntent);
    }
}
