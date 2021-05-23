package com.example.PocketCountry.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PocketCountry.Constants;
import com.example.PocketCountry.R;
import com.example.PocketCountry.data.CountryApi;
import com.example.PocketCountry.presentation.controller.MainController;
import com.example.PocketCountry.presentation.model.Country;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity2 extends AppCompatActivity {
    private MainController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");

        controller = new MainController(
                this,
            new GsonBuilder()
                    .setLenient()
                    .create(),
            getSharedPreferences(Constants.KEY_APP, Context.MODE_PRIVATE)
        );

        controller.onStart(id);

    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

}