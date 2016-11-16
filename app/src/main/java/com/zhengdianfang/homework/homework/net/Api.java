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

    String HOST = "http://thoughtworks-ios.herokuapp.com/";
    String USER_URL = "user/{username}";
    String TWEETS_URL = "user/{username}/tweets";

    @GET(USER_URL)
    Observable<JsonNode> getUserInfor(@Path("username") String username);

    @GET(TWEETS_URL)
    Observable<JsonNode> getTweets(@Path("username") String username);

}
