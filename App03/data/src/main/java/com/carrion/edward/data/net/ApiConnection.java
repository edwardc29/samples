package com.carrion.edward.data.net;

import android.content.Context;
import android.support.annotation.Nullable;

import com.carrion.edward.data.BuildConfig;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection {
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private Context context;
    private URL url;
    private String response;

    private ApiConnection(Context context, String url) throws MalformedURLException {
        this.context = context;
        this.url = new URL(BuildConfig.BASE_URL + url);
    }

    static ApiConnection createGET(Context context, String url) throws MalformedURLException {
        return new ApiConnection(context, url);
    }

    @Nullable
    String requestSyncCall() {
        connectToApi();
        return response;
    }

    private void connectToApi() {
        OkHttpClient okHttpClient = this.createClient();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        try {
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder
                .connectionPool(new ConnectionPool()) // By default 5 idle connections with 5 min TTL
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(new ChuckInterceptor(context));
        }

        return clientBuilder.build();
    }
}