package com.example.mynewsfeed;

import com.example.mynewsfeed.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    //Recibimos la variable Pais desde Feed1
    //Pais = getIntent().getStringExtra("Pais");

    @GET("top-headlines?country=mx")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey

    );
}
