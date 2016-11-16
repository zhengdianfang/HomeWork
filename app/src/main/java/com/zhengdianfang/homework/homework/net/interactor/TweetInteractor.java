package com.zhengdianfang.homework.homework.net.interactor;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.zhengdianfang.homework.homework.bean.Tweet;
import com.zhengdianfang.homework.homework.net.Api;
import com.zhengdianfang.homework.homework.net.ApiClient;

import java.io.IOException;
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
     * @param listener
     */
    public void getTweetListRequest(String username , @NonNull OnFinishedListener<List<Tweet>> listener){
        ApiClient.get().request().create(Api.class).getTweets(username)
                .flatMap(new Func1<JsonNode, Observable<List<Tweet>>>() {
                    @Override
                    public Observable<List<Tweet>> call(JsonNode jsonNode) {
                        List<Tweet> tweetList = null;
                        if (jsonNode != null) {
                            try {
                                tweetList = ApiClient.get().json().readValue(jsonNode.toString(), new TypeReference<List<Tweet>>() {});
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        return Observable.just(tweetList);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tweets -> {
                    listener.onFinished(tweets);
                }, throwable -> {
                    listener.onError(throwable.getMessage());
                });
    }
}
