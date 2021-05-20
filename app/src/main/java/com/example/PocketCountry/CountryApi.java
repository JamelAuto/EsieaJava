package com.example.PocketCountry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("api.json")
    Call<List<Country>> getCountryResponse();
}
