package com.zhengdianfang.homework.homework.net;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by zheng on 2016/11/16.
 * request http client.
 */

public class ApiClient {

    public static final String TAG = "ApiClient";

    private static ApiClient sApiClient;
    private Retrofit mRealClient;
    private ObjectMapper mObjectMapper;


    public ApiClient() {
        mRealClient = new Retrofit.Builder().baseUrl(Api.HOST)
                .client(createClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(createObjectMapper()))
                .build();

        createObjectMapper();

    }

    public static ApiClient get() {
        if (sApiClient == null) {
            synchronized (ApiClient.class){
                if (sApiClient == null){
                    sApiClient = new ApiClient();
                }
            }
        }
        return sApiClient;
    }

    public ObjectMapper json(){
        return mObjectMapper;
    }

    public Retrofit request(){
        return mRealClient;
    }

    public void closeClient(){
        mRealClient = null;
        sApiClient = null;
    }

    /**
     * create  {@link okhttp3.OkHttpClient} Client.
     * @return
     */
    private OkHttpClient createClient() {
        OkHttpClient client =  new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
        return client;
    }

    /**
     * create {@link com.fasterxml.jackson.databind.ObjectMapper } object
     * @return
     */
    private ObjectMapper createObjectMapper() {
        mObjectMapper = new ObjectMapper();
        mObjectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mObjectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return mObjectMapper;
    }

}
