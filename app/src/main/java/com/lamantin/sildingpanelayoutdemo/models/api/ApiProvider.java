package com.lamantin.sildingpanelayoutdemo.models.api;


import com.lamantin.sildingpanelayoutdemo.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    public static Api getApi(String baseUrl) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.interceptors().add(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClientBuilder.build());
        return builder.build().create(Api.class);
    }
}
