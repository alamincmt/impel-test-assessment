package com.alamincmt.news.network;


import com.alamincmt.news.model.NewsResponse;
import com.google.gson.JsonElement;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("top-headlines?")
    Call<NewsResponse> getTopNews(@QueryMap Map<String, String> paramsMap);

    @GET("top-headlines")
    Call<NewsResponse> getTopNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);
}
