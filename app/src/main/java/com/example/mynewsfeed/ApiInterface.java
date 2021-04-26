package com.example.mynewsfeed;

import com.example.mynewsfeed.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface ApiInterface {


    @Headers("Content-Type: application/json")
    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey,
            @Query("category") String Tema

    );

}
