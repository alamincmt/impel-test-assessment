package com.alamincmt.news.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.alamincmt.news.BuildConfig;


public class Utils {
    public static String TAG = "";
    private static Utils instance = null;
    private Context context;
    private boolean isNetworkConnected;

    public static Utils getInstance(Context context){
        if(instance == null){
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
