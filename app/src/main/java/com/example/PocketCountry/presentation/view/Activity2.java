package com.example.PocketCountry.presentation.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.PocketCountry.presentation.Constants;
import com.example.PocketCountry.R;
import com.example.PocketCountry.presentation.Singletons;
import com.example.PocketCountry.presentation.controller.MainController;
import com.google.gson.GsonBuilder;

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
                Singletons.getGson(),
            Singletons.getSharedPreferences(getApplicationContext())
        );

        controller.onStart(id);

    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

}