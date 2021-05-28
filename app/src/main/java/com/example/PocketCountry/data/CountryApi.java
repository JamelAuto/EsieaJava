package com.example.PocketCountry.data;

import com.example.PocketCountry.presentation.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("api.json")
    Call<List<Country>> getCountryResponse();
}
