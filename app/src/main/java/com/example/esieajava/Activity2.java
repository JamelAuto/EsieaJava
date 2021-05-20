package com.example.esieajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Activity2 extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        textViewResult = findViewById(R.id.countryBody);

        DisplayCountryName(id);
        makeApiCall(id);
        backButton();
    }

    public void DisplayCountryName(final int position){
        String[] arrayCountrie = {"Autriche","Bénin","Cameroun","Cuba","Egypte","Finlande","France","Allemagne","Irlande","Jordanie"
                ,"Lettonie","Malte","Méxique","Népal","Rwanda","Serbie","Singapoure","Espagne","Togo","Uruguay"};
        final TextView countryName = (TextView) findViewById(R.id.countryDetail);
        countryName.setText(arrayCountrie[position]);
    }

    static final String BASE_URL = "https://jamelauto.github.io/Data/";
    private void makeApiCall(final int position){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CountryApi CountryApi = retrofit.create(CountryApi.class);

        Call<List<Country>> call = CountryApi.getCountryResponse();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Country> restCountryResponses = response.body();
                String content = ("Indicatif Téléphonique : " + restCountryResponses.get(position).getPhone() + "\n\n") + ("Nom de domaine : " + restCountryResponses.get(position).getWeb() + "\n\n") + ("Langue officielle : " + restCountryResponses.get(position).getLanguage() + "\n\n");
                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                showError();
            }
        });

    }
    private void showError() {
        Toast.makeText(getApplicationContext(), "API ERROR", Toast.LENGTH_SHORT).show();
    }

    public void backButton() {
        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}