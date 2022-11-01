package com.alamincmt.news.network;


import com.google.gson.JsonElement;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @GET("top-headlines?")
    Call<JsonElement> getTopNews(@QueryMap Map<String, String> paramsMap);
}
