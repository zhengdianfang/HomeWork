package com.zhengdianfang.homework.homework.net.interactor;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.zhengdianfang.homework.homework.bean.User;
import com.zhengdianfang.homework.homework.net.Api;
import com.zhengdianfang.homework.homework.net.ApiClient;

import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zheng on 2016/11/16.
 */

public class UserInteractor implements BaseInteractor {

    /**
     * request http  get User infor.
     * @param listener
     */
    public void getUserInforRequest(@NonNull OnFinishedListener<User> listener){
        ApiClient.get().request().create(Api.class).getUserInfor()
                .flatMap(new Func1<JsonNode, Observable<User>>() {
                    @Override
                    public Observable<User> call(JsonNode jsonNode) {
                        User user = null;
                        if (jsonNode != null) {
                            try {
                                user = ApiClient.get().json().readValue(jsonNode.toString(), User.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        return Observable.just(user);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    listener.onFinished(user);
                }, throwable -> {
                    listener.onError(throwable.getMessage());
                });
    }
}
