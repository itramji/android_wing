package com.csoft.wing.manager.network;

import com.csoft.wing.common.AppConstants;
import com.csoft.wing.manager.network.api.RegisterUser;
import com.csoft.wing.manager.network.model.Registration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public class NetworkManager {

    private static final String TAG = "NETWORK_MANAGER";

    public static void registerUser(String mobileNumber,
                                    String mobileCode,
                                    String countryCode,
                                    String deviceCode,
                                    Callback<Registration> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterUser api = retrofit.create(RegisterUser.class);
        Call<Registration> call = api.registerUser(mobileNumber, mobileCode, countryCode, deviceCode);
        call.enqueue(listener);
    }


}
