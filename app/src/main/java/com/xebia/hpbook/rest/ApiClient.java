package com.xebia.hpbook.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ApiClient class
 */
public class ApiClient {

    /**
     * Base url
     */
    public static final String BASE_URL = "http://henri-potier.xebia.fr";

    private static Retrofit retrofit = null;

    /**
     *
     * @return retrofit
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
