package com.example.finalassignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static final String API_KEY = "ea676166c55de69edb885f0325690a09";
    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    public static String getApiKey() { return API_KEY;}
}
