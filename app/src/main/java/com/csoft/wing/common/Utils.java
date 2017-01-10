package com.csoft.wing.common;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class Utils {

    public static Cursor getContacts(Context context) {
        return context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
    }

}
