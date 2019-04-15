package com.niken.perkembangananak;

import android.app.Application;
import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.niken.perkembangananak.interceptors.AddCookiesInterceptor;
import com.niken.perkembangananak.interceptors.ReceivedCookiesInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by Firman on 12/15/2018.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SessionHandler sessionHandler = new SessionHandler(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(sessionHandler))
                .addInterceptor(new ReceivedCookiesInterceptor(sessionHandler))
                .build();

        AndroidNetworking.initialize(this, client);
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
