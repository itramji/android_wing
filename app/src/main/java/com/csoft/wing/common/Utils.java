package com.csoft.wing.common;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.ByteArrayOutputStream;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class Utils {

    public static Cursor getContacts(Context context) {
        return context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }

    public static EventBus getEventBus() {
        return EventBus.getDefault();
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkAvailable = false;
        if (context != null) {
            ConnectivityManager conMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = conMgr.getActiveNetworkInfo();

            isNetworkAvailable = info != null && info.isConnected();
            return isNetworkAvailable;
        } else
            return isNetworkAvailable ? CustomPhoneStateListener.getInstance(context).isInCall() : isNetworkAvailable;

    }

    public static boolean isEmailValid(String email) {
        String reg = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        return !(email == null || !email.matches(reg));
    }

    public static boolean isPhNumberValid(String phNumberStr) {
        return phNumberStr.length() >= 10;
    }

    public static class CustomPhoneStateListener extends PhoneStateListener {

        //private static final String TAG = "PhoneStateChanged";
        Context context; //Context to make Toast if required
        public boolean isInCallBoolean = true;
        private static CustomPhoneStateListener phoneStateListener = null;

        public CustomPhoneStateListener(Context context) {
            super();
            this.context = context;
        }

        public static CustomPhoneStateListener getInstance(Context context) {
            if (phoneStateListener == null) {
                phoneStateListener = new CustomPhoneStateListener(context);
            }
            return phoneStateListener;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    //when Idle i.e no call
                    Toast.makeText(context, "Phone state Idle", Toast.LENGTH_LONG).show();
                    isInCallBoolean = true;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //when Off hook i.e in call
                    //Make intent and start your service here
                    Toast.makeText(context, "Phone state Off hook", Toast.LENGTH_LONG).show();
                    isInCallBoolean = false;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //when Ringing
                    Toast.makeText(context, "Phone state Ringing", Toast.LENGTH_LONG).show();
                    isInCallBoolean = false;
                    break;
                default:
                    isInCallBoolean = true;
                    break;
            }
        }

        public boolean isInCall() {
            return isInCallBoolean;
        }
    }

}
