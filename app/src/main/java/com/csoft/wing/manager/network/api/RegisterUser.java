package com.csoft.wing.manager.network.api;

import com.csoft.wing.manager.network.model.Registration;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tringapps-admin on 10/1/17.
 */

public interface RegisterUser {
    @GET("api.php?cmd=userlogin&platform=android")
    Call<Registration> registerUser(@Query("mobile_number") String mobileNumber,
                                    @Query("mobile_code") String mobileCode,
                                    @Query("country_code") String countryCode,
                                    @Query("device_id") String deviceId);
}
