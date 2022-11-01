package com.alamincmt.news.utils;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.alamincmt.news.BuildConfig;

import java.text.ParseException;


public class Utils {
    public static String TAG = "";
    private static Utils instance = null;
    private Context context;
    private boolean isNetworkConnected;

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }

        return instance;
    }

    public Utils(Context context) {
        TAG = this.getClass().getSimpleName();
        this.context = context;
        instance = this;
    }

    public void printLog(String logKey, String logValue) {
        if (BuildConfig.DEBUG) {
            Log.d(logKey, logValue);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String convertDate(String date) {
        String convertedDate = date;
        SimpleDateFormat targetSDF = new SimpleDateFormat("MM-dd-yyyy");
        try {
            convertedDate = targetSDF.parse(date).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        isNetworkConnected = true; // Global Static Variable
                    }

                    @Override
                    public void onLost(Network network) {
                        isNetworkConnected = false; // Global Static Variable
                    }
                });
                isNetworkConnected = false;
            } catch (Exception e) {
                isNetworkConnected = false;
            }

            return isNetworkConnected;
        } else {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isNetworkConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            return isNetworkConnected;
        }
    }
}
