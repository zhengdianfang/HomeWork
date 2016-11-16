package com.zhengdianfang.homework.homework.net.interactor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.net.Api;
import com.zhengdianfang.homework.homework.net.ApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zheng on 2016/11/16.
 * Tweet request methods.
 */

public class TweetInteractor implements BaseInteractor {

    /**
     * request http  get tweets
     * @param username
     */
    public Observable<List<Tweet>> getTweetListObervable(String username ){
        return ApiClient.get().request().create(Api.class).getTweets(username)
                .flatMap(new Func1<JsonNode, Observable<List<Tweet>>>() {
                    @Override
                    public Observable<List<Tweet>> call(JsonNode jsonNode) {
                        List<Tweet> tweetList = new ArrayList<>();
                        if (jsonNode != null) {
                            try {
                                List<Tweet> results = ApiClient.get().json().readValue(jsonNode.toString(), new TypeReference<List<Tweet>>() {});
                                //filte error datas.
                                Observable.from(results).filter(tweet -> tweet.sender != null).subscribe(tweet -> {
                                    tweetList.add(tweet);
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        return Observable.just(tweetList);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

    }





}
