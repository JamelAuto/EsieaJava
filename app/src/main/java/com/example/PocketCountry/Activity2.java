package com.example.PocketCountry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
    private TextView textViewResult;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        textViewResult = findViewById(R.id.countryBody);

        sharedPreferences = getSharedPreferences(Constants.KEY_APP, Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        DisplayCountryName(id);
        List<Country> countryList = getDataFromCache();
        if(countryList != null)  {
            useSavedList(countryList, id);
        }else{
            makeApiCall(id);
        }
        backButton();
    }

    private List<Country> getDataFromCache() {
        String jsonCountry  = sharedPreferences.getString(Constants.KEY_COUNTRY, null);
        if(jsonCountry == null){
            return null;
        }else{
            Type ListType = new TypeToken<List<Country>>(){}.getType();
            return gson.fromJson(jsonCountry, ListType);
        }
    }

    public void DisplayCountryName(final int position){
        String[] arrayCountrie = {"Autriche","Bénin","Cameroun","Cuba","Egypte","Finlande","France","Allemagne","Irlande","Jordanie"
                ,"Lettonie","Malte","Méxique","Népal","Rwanda","Serbie","Singapoure","Espagne","Togo","Uruguay"};
        final TextView countryName = (TextView) findViewById(R.id.countryDetail);
        countryName.setText(arrayCountrie[position]);
    }

    private void makeApiCall(final int position){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.KEY_GIT)
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
                saveList(restCountryResponses);
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

    private void saveList(List<Country> countryList) {
        String jsonString = gson.toJson(countryList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_COUNTRY, jsonString)
                .apply();
    }

    public void useSavedList(List<Country> countryList, final int position){
        String content = ("Indicatif Téléphonique : " + countryList.get(position).getPhone() + "\n\n") + ("Nom de domaine : " + countryList.get(position).getWeb() + "\n\n") + ("Langue officielle : " + countryList.get(position).getLanguage() + "\n\n");
        textViewResult.append(content);
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