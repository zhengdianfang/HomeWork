package com.zhengdianfang.homework.homework.net;

import com.fasterxml.jackson.databind.JsonNode;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zheng on 2016/11/16.
 * the request url constant and request interface methods.
 */

public interface Api {

    public static final String HOST = "http://thoughtworks-ios.herokuapp.com/";
    public static final String USER_URL = "user/jsmith";
    public static final String TWEETS_URL = "user/{username}/tweets";

    @GET(USER_URL)
    Observable<JsonNode> getUserInfor();

    @GET(TWEETS_URL)
    Observable<JsonNode> getTweets(@Path("username") String username);

}
