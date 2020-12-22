package com.rigby.pushit.service.tool;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTool {
    private static final int TIMEOUT = 10000 * 2;
    //    private final static String BASE_URL = "http://51.38.187.3";
    private final static String BASE_URL = "http://192.168.18.8:8080";

    public final static String CLIENT_ENDPOINT = "/api";
    public final static String QUERY_TOKEN = "access_token";

    public PushItApi pushItApi;

    private static RetrofitTool instance;

    public static RetrofitTool getInstance() {
        if (instance == null) instance = new RetrofitTool();
        return instance;
    }

    private RetrofitTool() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createHttpClient())
                .build();

        pushItApi = retrofit.create(PushItApi.class);
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private OkHttpClient createHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).readTimeout(TIMEOUT, TimeUnit.MILLISECONDS).connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS).build();

//        return new OkHttpClient.Builder().readTimeout(TIMEOUT, TimeUnit.MILLISECONDS).connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS).build();

    }
}
