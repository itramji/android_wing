package com.csoft.wing.common.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.csoft.wing.R;

/**
 * Created by tringapps-admin on 19/1/17.
 */

public class Alert {

    public static void showErrorAlert(Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage(R.string.please_select_country)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

}
