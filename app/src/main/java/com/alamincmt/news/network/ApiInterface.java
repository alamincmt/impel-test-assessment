package com.alamincmt.news.network;

import com.alamincmt.news.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<NewsResponse> getTopNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);
}
