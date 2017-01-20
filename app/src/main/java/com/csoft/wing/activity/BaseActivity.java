package com.csoft.wing.activity;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csoft.wing.R;


public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView backButton, addButton;
    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        backButton = (ImageView) findViewById(R.id.left_back_icon);
        addButton = (ImageView) findViewById(R.id.right_add_icon);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);

        hideActionBarTitle();
    }

    public void hideRightIcon() {
        addButton.setVisibility(View.GONE);
    }


    public void showRightIcon() {
        addButton.setVisibility(View.VISIBLE);
    }

    public void addRightClickListener(View.OnClickListener onClickListener) {
        showRightIcon();
        addButton.setOnClickListener(onClickListener);
    }

    public void hideBackButton() {
        backButton.setVisibility(View.GONE);
    }


    public void showBackButton() {
        backButton.setVisibility(View.VISIBLE);
    }

    /**
     * Hide Application Toolbar
     */
    protected void hideToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * Show Application Toolbar
     */
    protected void showToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    /**
     * Hide default ActionBar title
     */
    protected void hideActionBarTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    public void setToolbarTitle(String title) {
        toolbarTitle.setText(title);
    }

    public void showErrorMessage(String msg) {
        Snackbar snackbar = getSnakbar(findViewById(android.R.id.content), msg);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.sample_red));
        snackbar.show();
    }

    public void showSuccessMessage(String msg) {
        Snackbar snackbar = getSnakbar(findViewById(android.R.id.content), msg);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.sample_green));
        snackbar.show();
    }

    private Snackbar getSnakbar(View view, String msg) {
        final Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(18);

        sbView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        return snackbar;
    }

    protected FragmentTransaction getFragmentTransaction(Fragment toFragment, int layoutId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //transaction.setCustomAnimations(/*R.anim.slide_in_right, R.anim.slide_out_right,R.anim.slide_in_right,*/0,0,0, R.anim.slide_out_right);
        transaction.replace(layoutId, toFragment, toFragment.getClass().getSimpleName());
        return transaction;
    }

    public void replaceFragment(Fragment toFragment, boolean isAddToBackStack, int containerId) {
        FragmentTransaction transaction = getFragmentTransaction(toFragment, containerId);
        if (isAddToBackStack) {
            transaction.addToBackStack(toFragment.getClass().getName());
            transaction.commit();
            showToolbar();
            setToolbarTitle(toFragment.getClass().getSimpleName().replaceAll("Fragment", ""));
        } else {
            transaction.commitNow();
        }
    }

    protected void onPermissionGranted(String[] permissions, int requestCode){

    }

    protected void onPermissionDenied(){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            int totalGrant = 0;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    totalGrant += 1;
                }
            }
            if (totalGrant == grantResults.length) {
                onPermissionGranted(permissions, requestCode);
            } else {
                onPermissionDenied();
            }
        } else {
            onPermissionDenied();
        }
    }

}
