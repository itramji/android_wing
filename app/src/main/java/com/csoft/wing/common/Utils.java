package com.csoft.wing.common;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class Utils {

    public static Cursor getContacts(Context context) {
        return context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }

    public static EventBus getEventBus(){
        return EventBus.getDefault();
    }

    public static String getDeviceId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getImageUri ( Context inContext, Bitmap inImage ) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
    }

}
