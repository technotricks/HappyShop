package com.sephora.happyshop.manager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simtech on 19/8/2017.
 */

public class APIManager {

    public static String BASE_URL = "http://sephora-mobile-takehome-apple.herokuapp.com/api/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if(retrofit ==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }

}
