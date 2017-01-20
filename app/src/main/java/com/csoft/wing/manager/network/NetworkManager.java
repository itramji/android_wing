package com.csoft.wing.manager.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.csoft.wing.common.Utils;
import com.csoft.wing.manager.network.event.Event;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class NetworkManager {

    private static final String TAG = "NETWORK_MANAGER";

    public static void asyncGetHTTPClient(Context context, String url, RequestParams params, final Class model, final int eventID) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(context, url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode,
                                  Header[] headers,
                                  JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                Object object = gson.fromJson(response.toString(), model);

                Event event = new Event();
                event.setId(eventID);
                event.setObject(object);

                Log.e(TAG, "onSuccess: ");
                Utils.getEventBus().post(event);


            }

            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Event event = new Event();
                event.setId(eventID);
                event.setName(responseString);
                event.setObject(null);

                Log.e(TAG, "onFailure: ");

                Utils.getEventBus().post(event);
            }
        });
    }

    public static void asyncPostHTTPClient(Context context, String url, RequestParams params, final Class model, final int eventID) {
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");
        dialog.show();

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(context, url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode,
                                  Header[] headers,
                                  JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.e(TAG, "onSuccess: ");
                dialog.dismiss();

                Gson gson = new Gson();
                Object object = gson.fromJson(response.toString(), model);
                /**
                 * Preparing event Object
                 */
                Event event = new Event();
                event.setId(eventID);
                event.setObject(object);
                /**
                 * Broadcasting response
                 */
                Utils.getEventBus().post(event);


            }

            @Override
            public void onFailure(int statusCode,
                                  Header[] headers,
                                  String responseString,
                                  Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.e(TAG, "onFailure: " + responseString);
                dialog.dismiss();
                /**
                 * Preparing event Object
                 */
                Event event = new Event();
                event.setId(eventID);
                event.setName(responseString);
                event.setObject(null);
                /**
                 * Broadcasting response
                 */
                Utils.getEventBus().post(event);
            }
        });
    }


}
