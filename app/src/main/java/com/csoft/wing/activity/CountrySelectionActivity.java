package com.csoft.wing.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csoft.wing.R;
import com.csoft.wing.adapter.CountrySelectionAdapter;
import com.csoft.wing.common.ui.custom.views.IndexView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class CountrySelectionActivity extends BaseActivity implements IndexView.IndexListener {

    private RecyclerView mList;
    private JSONArray mArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);
        init();
    }

    private void init() {
        mArray = loadJSONFromAsset();
        mList = (RecyclerView) findViewById(R.id.country_list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(new CountrySelectionAdapter(this, mArray));

        IndexView indexView = (IndexView) findViewById(R.id.index_view);
        indexView.setListerner(this);
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

    @Override
    public void onIndexClickListener(String character) {
        try {
            int position = -1;
            for (int i = 0; i < mArray.length(); i++) {
                if (mArray.getJSONObject(i).getString("name").startsWith(character)) {
                    position = i;
                    break;
                }
            }
            ((LinearLayoutManager)mList.getLayoutManager()).scrollToPositionWithOffset(position, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
