package com.csoft.wing.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csoft.wing.R;
import com.csoft.wing.adapter.CountrySelectionAdapter;
import com.csoft.wing.adapter.item.decorator.StickyHeaderItemDecorator;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class CountrySelectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        init();
    }

    private void init() {
        RecyclerView countryList = (RecyclerView) findViewById(R.id.country_list);
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.addItemDecoration(new StickyHeaderItemDecorator());
        countryList.setAdapter(new CountrySelectionAdapter(this, loadJSONFromAsset()));
    }

    @Override
    public void onPermissionGranted(String[] permissions, int requestCode) {

    }

    @Override
    public void onPermissionDenied() {

    }

    private JSONArray loadJSONFromAsset() {
        try {
            InputStream is = getAssets().open("country.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return new JSONArray(json);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
