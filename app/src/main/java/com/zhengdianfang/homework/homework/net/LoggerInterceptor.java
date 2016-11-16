package com.zhengdianfang.homework.homework.net;

import com.orhanobut.logger.Logger;
import com.zhengdianfang.homework.homework.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

/**
 * Created by zheng on 2016/11/16.
 */

public class LoggerInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (BuildConfig.DEBUG) {
            Logger.t(ApiClient.TAG).d( String.format("Sending request %s",
                    request.url()));
        }
        Response response = chain.proceed(request);

        if (BuildConfig.DEBUG) {
            String responseString = response.body().string();
            Response newResponse = response.newBuilder().body(ResponseBody.create(response.body().contentType(),responseString.getBytes(Util.UTF_8))).build();

            Logger.t(ApiClient.TAG).json( responseString);

            response = newResponse;
        }


        return response;
    }
}
