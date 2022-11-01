package com.alamincmt.news.network;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.alamincmt.news.model.NewsResponse;
import com.alamincmt.news.utils.Constants;
import com.alamincmt.news.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestClient {
    private Context context;
    private Utils utils;
    private final static String TAG = "RestClient: ";

    private ResponseListener responseListener;


    public RestClient(Context context){
        this.context = context;
        utils = new Utils(context);
    }

    public interface ResponseListener {
        void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> responseElement);
        void onFailure(@NonNull Call<NewsResponse>call, @NonNull Throwable t);
    }

    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void getTopNews(String country, String category) {
        Map<String, String>  params =  new HashMap<>();
        params.put("apiKey", Constants.API_KEY);
        params.put("country", country);
        params.put("category", category);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NewsResponse> call = apiService.getTopNews(country, category, Constants.API_KEY);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (responseListener != null) responseListener.onResponse(call, response);
                Utils.getInstance(context).printLog("NewsData", response.toString());
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable throwable) {
                if (responseListener != null) responseListener.onFailure(call, throwable);
                showFailedMessage("Error Occurred!", throwable.getMessage());
            }
        });

        /*call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> responseElement) {
                try {
                    if(responseElement.body() != null){
                        JSONObject response = new JSONObject(responseElement.body().toString());

                        Utils.getInstance(context).printLog("NewsData", response.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement>call, @NonNull Throwable t) {
                showFailedMessage("Error Occurred!", t.getMessage());
            }
        });*/
    }

    public void showFailedMessage(String title, String message){
        try{
            AlertDialog.Builder builder =new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
