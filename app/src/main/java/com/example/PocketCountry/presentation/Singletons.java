package com.example.PocketCountry.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.PocketCountry.data.CountryApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static CountryApi countryApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    //    Singleton Gson
    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    //    Singleton API
    public static CountryApi getCountryApi(){
        if(countryApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.KEY_GIT)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            countryApiInstance = retrofit.create(CountryApi.class);
        }
        return  countryApiInstance;
    }
    //    Singleton SharedPreference
    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.KEY_APP, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
