package com.example.esieajava;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryApi {
    @GET("api.json")
    Call<RestCountryResponse> getCountryResponse();
}
