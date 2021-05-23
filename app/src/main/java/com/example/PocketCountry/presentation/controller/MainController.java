package com.example.PocketCountry.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.PocketCountry.presentation.Constants;
import com.example.PocketCountry.R;
import com.example.PocketCountry.presentation.Singletons;
import com.example.PocketCountry.presentation.model.Country;
import com.example.PocketCountry.presentation.view.Activity2;
import com.example.PocketCountry.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Activity2 view;
    private TextView textViewResult;

    public MainController(Activity2 view,Gson gson, SharedPreferences sharedPreferences){
        this.view = view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
        }

    public void onStart(final int id){
        textViewResult = view.findViewById(R.id.countryBody);
        List<Country> countryList = getDataFromCache();
        if(countryList != null)  {
            useSavedList(countryList, id);
        }else{
            makeApiCall(id);
        }
        backButton();
        }

    public void makeApiCall(final int position){
        Call<List<Country>> call = Singletons.getCountryApi().getCountryResponse();

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
                final TextView countryName = (TextView) view.findViewById(R.id.countryDetail);
                countryName.setText(restCountryResponses.get(position).getCountry());
                textViewResult.append(content);
            }
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                view.showError();
            }
        });

    }

    public void saveList(List<Country> countryList) {
        String jsonString = gson.toJson(countryList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_COUNTRY, jsonString)
                .apply();
    }

    public void useSavedList(List<Country> countryList, final int position){
        String content = ("Indicatif Téléphonique : " + countryList.get(position).getPhone() + "\n\n") + ("Nom de domaine : " + countryList.get(position).getWeb() + "\n\n") + ("Langue officielle : " + countryList.get(position).getLanguage() + "\n\n");
        final TextView countryName = (TextView) view.findViewById(R.id.countryDetail);
        countryName.setText(countryList.get(position).getCountry());
        textViewResult.append(content);
    }

    public void backButton() {
        Button button = (Button) view.findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(view, MainActivity.class);
        view.startActivity(intent);
    }

    public List<Country> getDataFromCache() {
        String jsonCountry  = sharedPreferences.getString(Constants.KEY_COUNTRY, null);
        if(jsonCountry == null){
            return null;
        }else{
            Type ListType = new TypeToken<List<Country>>(){}.getType();
            return gson.fromJson(jsonCountry, ListType);
        }
    }
}
